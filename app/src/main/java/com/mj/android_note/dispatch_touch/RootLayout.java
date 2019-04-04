package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
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
        DispatchTouchActivity.printEventLog(ev,"RootLayout ## dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printEventLog(ev,"RootLayout ## onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printEventLog(event,"RootLayout ## onTouchEvent");
        return super.onTouchEvent(event);
    }

}
