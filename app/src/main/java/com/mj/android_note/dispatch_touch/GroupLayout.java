package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:41
 * Email       : miaojian@conew.com
 * Description :
 */

public class GroupLayout extends RelativeLayout {

    private static final String TAG = DispatchTouchActivity.FULL_TAG + "GroupLayout";

    public GroupLayout(Context context) {
        super(context);
    }

    public GroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printLog(TAG, "dispatchTouchEvent : " + ev.getAction(), ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printLog(TAG, "onInterceptTouchEvent :" + ev.getAction(), ev);
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printLog(TAG, "onTouchEvent :" + event.getAction(), event);
        return super.onTouchEvent(event);
    }


}
