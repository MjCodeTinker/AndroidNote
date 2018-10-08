package com.mj.android_note.ui.activity.db;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.data.db.DbManager;
import com.mj.android_note.data.db.table.in.IFolder;
import com.mj.android_note.utils.LocalResourceUtil;
import com.mj.android_note.utils.LogUtil;
import com.mj.android_note.utils.ThreadUtils;
import com.mj.android_note.utils.ToastUtils;

import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/9/9--下午7:12
 * Email       : miaojian_666@126.com
 * Description : 数据库相关
 */
public class DbMainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "DbMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);
        initView();
    }

    private void initView() {
//        findViewById(R.id.db_save).setOnClickListener(this);
//        findViewById(R.id.db_find).setOnClickListener(this);
//        findViewById(R.id.db_delete).setOnClickListener(this);
        findViewById(R.id.header_back).setOnClickListener(this);
        findViewById(R.id.header_more).setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.header_title);
        tvTitle.setText(LocalResourceUtil.getStr(R.string.default_folder_name));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_back:
                finish();
                break;
            case R.id.header_more:

                break;
//            case R.id.db_save:
//                save();
//                break;
//            case R.id.db_find:
//                findAll();
//                break;
//            case R.id.db_delete:
//                delete();
//                break;
            default:
                break;
        }
    }

    private void delete() {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                FileOrFolderBean folderBean = new FileOrFolderBean();
                folderBean.setFileName("mj.html");
                final int result = DbManager.getInstance().getFolderTable().deleteFileOrFolder(folderBean);
                ThreadUtils.getInstance().postUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleDbResult(result);
                    }
                });
            }
        });
    }

    private void findAll() {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                List<FileOrFolderBean> childFolderContent = DbManager.getInstance().getFolderTable().findChildFolderContent(new FileOrFolderBean());
                LogUtil.d(TAG, "childFolderContentList = " + childFolderContent);
            }
        });
    }

    private void save() {
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                FileOrFolderBean fileOrFolderBean = new FileOrFolderBean();
                fileOrFolderBean.setFileName("mj.html");
                fileOrFolderBean.setCreateTime(System.currentTimeMillis());
                final int result = DbManager.getInstance().getFolderTable().insertFileOrFolder(fileOrFolderBean);
                ThreadUtils.getInstance().postUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleDbResult(result);
                    }
                });
            }
        });
    }

    private void handleDbResult(int result) {
        switch (result) {
            case IFolder.ResultCode.SUCCESS:
                ToastUtils.showShortToast("操作成功");
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
                break;
            default:
                break;
        }
    }
}