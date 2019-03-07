package com.mj.lib.base.log;

import android.util.Log;

/**
 * Author      : MJ
 * Date        : 2018/9/6--下午9:40
 * Email       : miaojian_666@126.com
 * Description : log日志工具类
 */
public class LogUtil {

    //是否为debug模式
    private static boolean isDebug = true;
    private static final String APP_SPECIAL_LOG_START = ">>logStart>>>>>>>>>>>>>";
    private static final String APP_SPECIAL_LOG_END = ">>>>>>>>>>>>>>>logEnd>>";

    private LogUtil() {
    }

    public static void setIsDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }


    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, APP_SPECIAL_LOG_START + msg + APP_SPECIAL_LOG_END);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, APP_SPECIAL_LOG_START + msg + APP_SPECIAL_LOG_END);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, APP_SPECIAL_LOG_START + msg + APP_SPECIAL_LOG_END);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, APP_SPECIAL_LOG_START + msg + APP_SPECIAL_LOG_END);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, APP_SPECIAL_LOG_START + msg + APP_SPECIAL_LOG_END);
        }
    }

}
