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
 * Date        : 2018/12/20--16:21
 * Email       : miaojian@conew.com
 * Description : 第二个进程页面
 */

public class SecondProcessActivity extends BaseActivity {

    /**
     * 测试多进程中的常量，修改后的值
     */
    public static int TEST_CONSTANT_FROM_MULTI_PROCESS_RESULT = 200;

    public static void launcher(Context context) {
        startAct(context, SecondProcessActivity.class);
    }

    private TextView tvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_process);
        tvText = findViewById(R.id.activity_second_process_text);
        findViewById(R.id.activity_second_process_btn_update_constant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateConstantsAndProcessSync();
            }
        });
    }

    private void updateConstantsAndProcessSync() {
        errorUpdateMethod();
        setText();
    }

    /**
     * 错误的修改方式
     */
    private void errorUpdateMethod() {
        MainProcessActivity.TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT = TEST_CONSTANT_FROM_MULTI_PROCESS_RESULT;
    }


    @Override
    protected void onResume() {
        super.onResume();
        setText();
    }

    private void setText() {
        tvText.setText(String.valueOf(MainProcessActivity.TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT));
    }


}
