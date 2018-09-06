package com.mj.android_note.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mj.android_note.R;
import com.mj.android_note.data.db.table.FolderTable;
import com.mj.android_note.utils.ToastUtils;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 应用的主界面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FolderTable folderTable = new FolderTable();
        ToastUtils.showShortToast("result:" + true);

    }


}
