package com.mj.android_note.dispatch_touch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import com.mj.android_note.R;
import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:49
 * Email       : miaojian@conew.com
 * Description : 事件分发分析页面
 */

public class DispatchTouchActivity extends AppCompatActivity {

    private static final String TAG = "DispatchTouchActivity";

    public static void launcher(Context context) {
        Intent intent = new Intent(context, DispatchTouchActivity.class);
        context.startActivity(intent);
    }

    public static void printEventLog(MotionEvent ev, String from) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.e(TAG, from + " ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e(TAG, from + " ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.e(TAG, from + " ACTION_CANCEL");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch);
    }
}
