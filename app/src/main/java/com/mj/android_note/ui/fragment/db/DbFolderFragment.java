package com.mj.android_note.ui.fragment.db;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.data.db.DbManager;
import com.mj.android_note.ui.adapter.FolderContentAdapter;
import com.mj.android_note.ui.fragment.BaseFragment;
import com.mj.lib.base.thread.ThreadUtils;

import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/10/27--上午12:19
 * Email       : miaojian_666@126.com
 * Description : 文件夹数据库fragment
 */
public class DbFolderFragment extends BaseFragment {

    public static final String TAG = "DbFolderFragment";

    public static DbFolderFragment newInstance(Bundle bundle) {
        DbFolderFragment dbFolderFragment = new DbFolderFragment();
        dbFolderFragment.setArguments(bundle);
        return dbFolderFragment;
    }


    private FolderContentAdapter adapter = new FolderContentAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_db_folder;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = rootView.findViewById(R.id.folder_fragment_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);
    }

    public void refreshUi(int parentFolderId) {
        final FileOrFolderBean fileOrFolderBean = new FileOrFolderBean();
        fileOrFolderBean.setParentFolderID(parentFolderId);
        ThreadUtils.getInstance().exeDbAction(new Runnable() {
            @Override
            public void run() {
                final List<FileOrFolderBean> childFolderContent = DbManager.getInstance().getFolderTable().findChildFolderContent(fileOrFolderBean);
                ThreadUtils.getInstance().postUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refreshList(childFolderContent);
                    }
                });
            }
        });
    }
}
