package com.mj.android_note.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

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
     * 检查设备是否有Navigation bar
     * @param context context
     * @return 是否有NavigationBar
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return false;
        }
        int screenHeight = getScreenHeight(context);
        int screenRealHeight = getScreenRealHeight(context);
        return screenRealHeight - screenHeight > 0;
    }

    /**
     * 获取屏幕高度
     *
     * @param context context
     * @return 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    /**
     * 获取屏幕的真是高度
     *
     * @param context context
     * @return 屏幕的真是高度
     */
    public static int getScreenRealHeight(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(displayMetrics);
        } else {
            display.getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }


    /**
     * 获取display
     *
     * @param context context
     * @return display 对象
     */
    private static Display getDisplay(Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (windowManager != null) {
            return windowManager.getDefaultDisplay();
        }
        return null;
    }

    /**
     * 获取windowsManager
     *
     * @param context context
     * @return windowsManager
     */
    private static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
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
