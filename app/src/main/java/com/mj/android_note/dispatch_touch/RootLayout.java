package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:41
 * Email       : miaojian@conew.com
 * Description :
 */

public class RootLayout extends LinearLayout {

    private static final String TAG = DispatchTouchActivity.FULL_TAG + "RootLayout";

    public RootLayout(Context context) {
        super(context);
    }

    public RootLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printLog(TAG, "dispatchTouchEvent : " + ev.getAction(), ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printLog(TAG, "onInterceptTouchEvent : " + ev.getAction(), ev);
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printLog(TAG, "onTouchEvent : " + event.getAction(), event);
        return super.onTouchEvent(event);
    }

}
