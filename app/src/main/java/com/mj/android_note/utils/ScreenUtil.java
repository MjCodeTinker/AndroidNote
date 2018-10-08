package com.mj.android_note.utils;

import android.content.Context;

import com.mj.android_note.app.NoteApplication;

/**
 * Author      : MJ
 * Date        : 2018/10/8--15:17
 * Email       : miaojian@conew.com
 * Description : 屏幕相关的操作
 */

public class ScreenUtil {


    private static Context getContext() {
        return NoteApplication.getInstance();
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }





}
