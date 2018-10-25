package com.mj.android_note.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

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
        return getBarHeight("status_bar_height");
    }

    /**
     * 获取虚拟按键的高度
     *
     * @return 虚拟按键的高度
     */
    public static int getNavigationBarHeight() {
        return getBarHeight("navigation_bar_height");
    }

    /**
     * 获取状态栏 或虚拟按键高度
     *
     * @param barName 名称
     * @return 高度
     */
    private static int getBarHeight(String barName) {
        int BarHeight = 0;
        int resourceId = getContext().getResources().getIdentifier(barName, "dimen", "android");
        if (resourceId > 0) {
            BarHeight = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return BarHeight;
    }


    /**
     * 检查有没有导航条
     *
     * @param activity activity
     * @return 是否有navigationBar
     */
    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !hasMenuKey && !hasBackKey;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public static float px2dp(int pxValue) {
        return (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }


}
