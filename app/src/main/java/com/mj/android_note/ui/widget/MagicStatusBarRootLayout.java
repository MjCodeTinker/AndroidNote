package com.mj.android_note.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.mj.android_note.R;
import com.mj.android_note.utils.LocalResourceUtil;
import com.mj.android_note.utils.ScreenUtil;
import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2018/10/8--15:26
 * Email       : miaojian@conew.com
 * Description : 沉侵式状态栏颜色布局
 */

public class MagicStatusBarRootLayout extends LinearLayout {

    private static final String TAG = "MagicStatusBarRootLayout";

    public MagicStatusBarRootLayout(Context context) {
        this(context, null);
    }

    public MagicStatusBarRootLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicStatusBarRootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        initStatusColor(context, attrs);
    }

    private void initStatusColor(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MagicStatusBarRootLayout);
        int statusBarColor = t.getColor(R.styleable.MagicStatusBarRootLayout_statusColor, LocalResourceUtil.getColor(R.color.colorAccent));
        t.recycle();
        View view = new View(context);
        LogUtil.d(TAG, "color=" + statusBarColor);
        view.setBackgroundColor(statusBarColor);
        addView(view, LayoutParams.MATCH_PARENT, ScreenUtil.getStatusBarHeight());
    }

}
