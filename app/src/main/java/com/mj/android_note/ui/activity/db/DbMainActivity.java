package com.mj.android_note.ui.activity.db;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.base.BaseActivity;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.data.db.DbManager;
import com.mj.android_note.data.db.table.DbFolderImpl;
import com.mj.android_note.data.db.table.in.IFolder;
import com.mj.android_note.ui.fragment.db.DbFolderFragment;
import com.mj.android_note.ui.window.SimpleDialog;
import com.mj.android_note.utils.LocalResourceUtil;
import com.mj.android_note.utils.ToastUtils;
import com.mj.lib.base.log.LogUtil;
import com.mj.lib.base.thread.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/9/9--下午7:12
 * Email       : miaojian_666@126.com
 * Description : 数据库相关
 */
public class DbMainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DbMainActivity";
    private static final String KEY_PARENT_FOLDER_ID = "key_parent_folder_id";
    private TextView tvTitle;
    private ImageView ivMore;
    private SimpleDialog createFolderOrFileDialog;
    private DbFolderFragment dbFolderFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);
        initView();
        initFragment(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_PARENT_FOLDER_ID, DbFolderImpl.DEFAULT_PARENT_FOLDER_ID);
    }

    private void initFragment(Bundle savedInstanceState) {
        dbFolderFragment = (DbFolderFragment) getSupportFragmentManager().findFragmentByTag(DbFolderFragment.TAG);
        if (dbFolderFragment == null) {
            dbFolderFragment = DbFolderFragment.newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.db_main_fragment_container, dbFolderFragment)
                    .commitNow();
        }

        if (savedInstanceState != null) {
            String parentFolderId = savedInstanceState.getString(KEY_PARENT_FOLDER_ID);
            if (!TextUtils.isEmpty(parentFolderId)) {
                dbFolderFragment.refreshUi(Integer.parseInt(parentFolderId));
            }
        }

    }

    private void initView() {

        ivMore = findViewById(R.id.header_more);
        tvTitle = findViewById(R.id.header_title);
        findViewById(R.id.header_back).setOnClickListener(this);
        ivMore.setOnClickListener(this);
        tvTitle.setText(LocalResourceUtil.getStr(R.string.default_folder_name));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_back:
                finish();
                break;
            case R.id.header_more:
                menuClick();
                break;
            default:
                break;
        }
    }

    private void menuClick() {
        final SimpleDialog simpleDialog = new SimpleDialog(this);
        final String newFolderStr = LocalResourceUtil.getStr(R.string.db_new_folder);
        final String newContentStr = LocalResourceUtil.getStr(R.string.db_new_content);

        List<String> contents = new ArrayList<>();
        contents.add(newFolderStr);
        contents.add(newContentStr);
        simpleDialog.initListDialog(ivMore, contents, new SimpleDialog.OnItemClickListener() {
            @Override
            public void onclick(String itemName) {

                createFolderOrFileDialog = new SimpleDialog(DbMainActivity.this);
                final boolean createFolder = newFolderStr.equals(itemName);
                createFolderOrFileDialog.initCreateFolderOrFileDialog(createFolder, new SimpleDialog.FolderOrFileDialogListener<FileOrFolderBean>() {
                    @Override
                    public void negative() {
                        createFolderOrFileDialog.dismiss();
                    }

                    @Override
                    public void positive(FileOrFolderBean bean) {
                        save(bean, createFolder);
                    }
                });
                createFolderOrFileDialog.show();
                simpleDialog.dismiss();
            }
        });
        simpleDialog.show();
//        simpleDialog.initListDialogWindow(ivMore);
    }

    private void delete(final boolean isCreateFolder) {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                FileOrFolderBean folderBean = new FileOrFolderBean();
                folderBean.setFileName("mj.html");
                final int result = DbManager.getInstance().getFolderTable().deleteFileOrFolder(folderBean);
                ThreadUtils.getInstance().postUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleDbResult(isCreateFolder, result);
                    }
                });
            }
        });
    }

    private void findAll(final boolean isCreateFolder) {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                List<FileOrFolderBean> childFolderContent = DbManager.getInstance().getFolderTable().findChildFolderContent(new FileOrFolderBean());
                LogUtil.d(TAG, "childFolderContentList = " + childFolderContent);
            }
        });
    }

    private void save(@NonNull final FileOrFolderBean fileOrFolderBean, final boolean isCreateFolder) {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                final int result = DbManager.getInstance().getFolderTable().insertFileOrFolder(fileOrFolderBean);
                ThreadUtils.getInstance().postUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleDbResult(isCreateFolder, result);
                    }
                });
            }
        });
    }

    private void handleDbResult(boolean isCreateFolder, int result) {
        switch (result) {
            case IFolder.ResultCode.SUCCESS:
                if (createFolderOrFileDialog != null && createFolderOrFileDialog.isShowing()) {
                    createFolderOrFileDialog.dismiss();
                }
                ToastUtils.showShortToast(isCreateFolder ? "创建文件夹成功" : "保存文件成功");
                break;
            case IFolder.ResultCode.ERROR_FILE_EXIST:
                ToastUtils.showShortToast("文件已经存在");
                break;
            case IFolder.ResultCode.ERROR_FOLDER_EXIST:
                ToastUtils.showShortToast("文件夹已经存在");
                break;
            case IFolder.ResultCode.ERROR_FILE_NOT_EXIST:
                ToastUtils.showShortToast("文件不存在");
                break;
            case IFolder.ResultCode.ERROR_FOLDER_NOT_EXIST:
                ToastUtils.showShortToast("文件夹不存在");
                break;
            case IFolder.ResultCode.ERROR_FILE_NAME_IS_NULL:
                ToastUtils.showShortToast("文件名字不能为空");
                break;
            case IFolder.ResultCode.ERROR_FOLDER_NAME_IS_NULL:
                ToastUtils.showShortToast("文件夹名字不能为空");
                break;
            case IFolder.ResultCode.ERROR_UN_KNOW:
                ToastUtils.showShortToast("未知错误");
                break;
            default:
                break;
        }
    }

    /**
     * 需要传入fragment中的接口
     */
    RefreshTitleCallback refreshTitleCallback = new RefreshTitleCallback() {
        @Override
        public void updateTitle(String titleName) {
            if (tvTitle != null) {
                tvTitle.setText(titleName);
            }
        }
    };


    public interface RefreshTitleCallback {
        void updateTitle(String titleName);
    }


}
