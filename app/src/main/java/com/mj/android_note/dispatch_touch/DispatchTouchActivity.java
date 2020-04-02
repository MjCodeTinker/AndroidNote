package com.mj.android_note.dispatch_touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mj.android_note.R;
import com.mj.lib.base.log.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author      : MJ
 * Date        : 2019/4/4--16:49
 * Email       : miaojian@conew.com
 * Description : 事件分发分析页面
 */

public class DispatchTouchActivity extends AppCompatActivity {

    public static final String FULL_TAG = "EventDispatch###";
    private static final String TAG = FULL_TAG + "DispatchTouchActivity";

    public static void launcher(Context context) {
        Intent intent = new Intent(context, DispatchTouchActivity.class);
        context.startActivity(intent);
    }

    public static void printEventLog(MotionEvent ev, String from) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                appendContent(from);
                appendContent(" ACTION_DOWN -> \n");
                LogUtil.e(TAG, from + " ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                appendContent(from);
                appendContent(" ACTION_UP -> \n");
                LogUtil.e(TAG, from + " ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                appendContent(from);
                appendContent(" ACTION_CANCEL -> \n");
                LogUtil.e(TAG, from + " ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_MOVE:
                appendContent(from);
                appendContent(" ACTION_MOVE -> \n");
                LogUtil.e(TAG, from + " ACTION_MOVE");
                break;
            default:
                break;
        }
    }

    private static void appendContent(String content) {
        if (!isAppend) {
            return;
        }
        stringBuilder.append(content);
    }

    private static StringBuilder stringBuilder;
    private static boolean isAppend = true;

    @BindView(R.id.dispatch_touch_root_layout)
    RootLayout rootLayout;

    @BindView(R.id.dispatch_touch_group_layout)
    GroupLayout groupLayout;

    @BindView(R.id.dispatch_touch_display_result)
    TouchView touchView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch);
        ButterKnife.bind(this);
        stringBuilder = new StringBuilder();

        setOnTouchEvent(rootLayout);
        setOnClickListener(rootLayout);
        setOnLongClickListener(rootLayout);

        setOnTouchEvent(groupLayout);
        setOnClickListener(groupLayout);
        setOnLongClickListener(groupLayout);

        setOnTouchEvent(touchView);
        setOnClickListener(touchView);
        setOnLongClickListener(touchView);

    }

    private void setOnTouchEvent(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                printLog(FULL_TAG, view.getClass().getSimpleName() + ": onTouch  = " + event.getAction(), event);
                return false;
            }
        });
    }

    private void setOnClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog(FULL_TAG, view.getClass().getSimpleName() + "--click Event");
            }
        });
    }

    private void setOnLongClickListener(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                printLog(FULL_TAG, view.getClass().getSimpleName() + "--onLongClick Event");
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dispatch_touch_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_touch_content:
                touchView.setText(stringBuilder.toString());
                break;
            case R.id.clear:
                stringBuilder.setLength(0);
                stringBuilder.trimToSize();
                break;
            case R.id.no_append:
                isAppend = false;
                break;
            case R.id.append:
                isAppend = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        LogUtil.e(TAG, "onUserInteraction 被调用");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        printLog(TAG, "dispatchTouchEvent : " + ev.getAction(), ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        printLog(TAG, "onTouchEvent : " + event.getAction(), event);
        return super.onTouchEvent(event);
    }


    public static void printLog(String tag, String str) {
        stringBuilder.append(str).append("\n");
        Log.e(tag, str);
    }

    public static void printLog(String tag, String str, MotionEvent motionEvent) {
        if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
            return;
        }
        printLog(tag, str);
    }
}
