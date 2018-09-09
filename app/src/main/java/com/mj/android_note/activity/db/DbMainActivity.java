package com.mj.android_note.activity.db;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.data.db.DbManager;

/**
 * Author      : MJ
 * Date        : 2018/9/9--下午7:12
 * Email       : miaojian_666@126.com
 * Description : 数据库相关
 */
public class DbMainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);
        findViewById(R.id.db_save).setOnClickListener(this);
        findViewById(R.id.db_find).setOnClickListener(this);
        findViewById(R.id.db_delete).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.db_save:
                FileOrFolderBean fileOrFolderBean=new FileOrFolderBean();
                fileOrFolderBean.setFileName("mj.html");
                fileOrFolderBean.setCreateTime(System.currentTimeMillis());
                DbManager.getInstance().getFolderTable().insertFileOrFolder(fileOrFolderBean);
                break;
            case R.id.db_find:

                break;
            case R.id.db_delete:

                break;
            default:
                break;
        }
    }
}
