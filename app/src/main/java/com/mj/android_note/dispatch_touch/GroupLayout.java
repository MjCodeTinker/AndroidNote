package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:41
 * Email       : miaojian@conew.com
 * Description :
 */

public class GroupLayout extends RelativeLayout {

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
        DispatchTouchActivity.printEventLog(ev, "GroupLayout ## dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        DispatchTouchActivity.printEventLog(ev, "GroupLayout ## onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printEventLog(event, "GroupLayout ## onTouchEvent");
        return super.onTouchEvent(event);
    }


}
