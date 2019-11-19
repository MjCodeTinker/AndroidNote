package com.mj.android_note.module.process;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.base.BaseActivity;

/**
 * Author      : MJ
 * Date        : 2018/12/20--16:23
 * Email       : miaojian@conew.com
 * Description : 第一个进程Activity
 */

public class FirstProcessActivity extends BaseActivity {

    public static void launcher(Context context) {
        startAct(context, FirstProcessActivity.class);
    }

    private TextView tvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_process);
        tvText = findViewById(R.id.activity_first_process_text);
        findViewById(R.id.activity_first_process_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondProcessActivity.launcher(FirstProcessActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvText.setText(String.valueOf(MainProcessActivity.TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT));
    }

}
