package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:44
 * Email       : miaojian@conew.com
 * Description :
 */

@SuppressLint("AppCompatCustomView")
public class TouchView extends TextView {

    private static final String TAG = DispatchTouchActivity.FULL_TAG + "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printLog(TAG, "dispatchTouchEvent : " + event.getAction(), event);
        return super.dispatchTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DispatchTouchActivity.printLog(TAG, "onTouchEvent : " + event.getAction(), event);
        return super.onTouchEvent(event);
    }


}
