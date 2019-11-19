package com.mj.android_note.dispatch_touch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    private TouchView touchView;
    private static boolean isAppend = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch);
        stringBuilder = new StringBuilder();
        touchView = findViewById(R.id.dispatch_touch_display_result);
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        printEventLog(ev, "ACTIVITY ## dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        printEventLog(event, "ACTIVITY ## onTouchEvent");
        return super.onTouchEvent(event);
    }
}
