package com.mj.test.module.test_message_train;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.mj.lib.base.communication.app_inner.MessageTrain;
import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.lib.base.communication.app_inner.type.ThreadMode;
import com.mj.lib.base.log.LogUtil;
import com.mj.lib.base.thread.ThreadUtils;
import com.mj.lib.base.ui.ToastUtils;
import com.mj.test.module.R;
import com.mj.test.module.test_message_train.bean.FragmentMsg;
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
                MessageTrain.getDefault().post(new FragmentMsg("小鲜肉程序员一枚 ~~ 全力冲刺中..."));
                break;
            default:
                break;
        }
    }


    @Subscriber(threadMode = ThreadMode.MAIN)
    public void showMessage(UserInfo userInfo) {
        ToastUtils.showShortToast(TestMessageTrainActivity.this, userInfo.name + "--" + userInfo.age + "--thread=" + Thread.currentThread().getName());
    }

    @SuppressLint("LongLogTag")
    @Subscriber(threadMode = ThreadMode.WORK_THREAD)
    public void logMessage(LogMsg logMsg) {
        final String threadName = Thread.currentThread().getName();
        ThreadUtils.getInstance().postUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToast(TestMessageTrainActivity.this, "收到打印日志消息，消息发送的线程是 ： " + threadName);
            }
        });
        LogUtil.e(TAG, "logMsg=" + logMsg.text + "--thread=" + threadName);
    }

    @Override
    protected void onDestroy() {
        MessageTrain.getDefault().unRegister(this);
        super.onDestroy();
    }


}
