package com.mj.android_note.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mj.android_note.R;
import com.mj.android_note.libary.adaptive.StartSystemSettingPageImpl;
import com.mj.android_note.libary.adaptive.SystemSettingPageEntity;
import com.mj.android_note.module.process.MainProcessActivity;
import com.mj.android_note.ui.activity.db.DbMainActivity;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 应用的主界面
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_activity_btn_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.main_activity_btn_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemSettingPageEntity adaptationEntity = new SystemSettingPageEntity();
                adaptationEntity.setContext(MainActivity.this);
                new StartSystemSettingPageImpl().beganToFit(adaptationEntity);
            }
        });


        findViewById(R.id.main_activity_btn_process_communication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainProcessActivity.launcher(MainActivity.this);
            }
        });


    }

}
