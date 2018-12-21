package com.mj.android_note.module.process;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.base.BaseActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process);
        tvText = findViewById(R.id.activity_main_process_text);
        findViewById(R.id.activity_main_process_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstProcessActivity.launcher(MainProcessActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvText.setText(String.valueOf(TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT));
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT = 100;
    }
}
