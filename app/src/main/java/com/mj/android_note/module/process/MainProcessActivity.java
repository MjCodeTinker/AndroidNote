package com.mj.android_note.module.process;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.base.BaseActivity;
import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2018/12/20--16:24
 * Email       : miaojian@conew.com
 * Description : 主进程页面
 */

public class MainProcessActivity extends BaseActivity {


    /**
     * 测试多进程中的常量同步修改，默认值为100
     */
    public static int TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT = 100;

    public static void launcher(Context context) {
        startAct(context, MainProcessActivity.class);
    }

    private TextView tvText;

    private BackgroundService backgroundService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process);
        tvText = findViewById(R.id.activity_main_process_text);
        findViewById(R.id.activity_main_process_btn).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
//              FirstProcessActivity.launcher(MainProcessActivity.this);
                Intent intent = new Intent(MainProcessActivity.this, BackgroundService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                int add = backgroundService.add(10, 8);
//                LogUtil.e(ProcessConstants.TAG, "result = " + add);
//                tvText.setText("结果为：" + add);
            }
        });
    }

    /**
     * service Connection
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            backgroundService = ((BackgroundService.SerViceBinder) service).getService();
            LogUtil.e(ProcessConstants.TAG, "onServiceConnected," + name + "--service:" + service.getClass().getName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.e(ProcessConstants.TAG, "onServiceDisconnected,name:" + name);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }

}
