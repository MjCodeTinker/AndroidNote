package com.mj.test.module.test_message_train;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.mj.lib.base.communication.app_inner.MessageTrain;
import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.lib.base.communication.app_inner.type.ThreadMode;
import com.mj.test.module.R;
import com.mj.test.module.test_message_train.bean.LogMsg;
import com.mj.test.module.test_message_train.bean.UserInfo;

/**
 * Author      : MJ
 * Date        : 2019/3/7--13:18
 * Email       : miaojian@conew.com
 * Description : 测试 MessageTrain 框架的页面
 */

public class TestMessageTrainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestMessageTrainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_message_train);

        //register
        MessageTrain.getDefault().register(this);

        findViewById(R.id.test_message_train_btn_show_toast).setOnClickListener(this);
        findViewById(R.id.test_message_train_btn_print_log).setOnClickListener(this);
        findViewById(R.id.test_message_train_btn_send_msg_to_fragment).setOnClickListener(this);

        TestMessageTrainFragment testMessageTrainFragment = TestMessageTrainFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.test_message_train_container, testMessageTrainFragment)
                .show(testMessageTrainFragment)
                .commitNow();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_message_train_btn_show_toast:
                MessageTrain.getDefault().post(new UserInfo("MjCodeTinker", "25岁"));
                break;
            case R.id.test_message_train_btn_print_log:
                MessageTrain.getDefault().post(new LogMsg("日志信息"));
                break;
            case R.id.test_message_train_btn_send_msg_to_fragment:
                MessageTrain.getDefault().post(new UserInfo("老龄化程序员", "~~到了翻车的年纪"));
                break;
            default:
                break;
        }
    }


    @Subscriber(threadMode = ThreadMode.MAIN)
    public void showMessage(UserInfo userInfo) {
        Toast.makeText(this, userInfo.name + "--" + userInfo.age + "--thread=" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("LongLogTag")
    @Subscriber(threadMode = ThreadMode.WORK_THREAD)
    public void logMessage(LogMsg logMsg) {
        Log.e(TAG, "logMsg=" + logMsg.text + "--thread=" + Thread.currentThread().getName());
    }


    @Override
    protected void onDestroy() {
        MessageTrain.getDefault().unRegister(this);
        super.onDestroy();
    }


}
