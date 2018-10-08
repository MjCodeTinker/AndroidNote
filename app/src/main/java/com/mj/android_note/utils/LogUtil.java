package com.mj.android_note.utils;

import android.util.Log;

import com.mj.android_note.app.AppConfig;
import com.mj.android_note.utils.constant.ExceptionMessageConstant;

/**
 * Author      : MJ
 * Date        : 2018/9/6--下午9:40
 * Email       : miaojian_666@126.com
 * Description : log日志工具类
 */
public class LogUtil {
    //是否为debug模式
    private static final boolean isDebug = AppConfig.AppEnv.isDebug;
    private static final String APP_SPECIAL_LOG_START = "##logStart##";
    private static final String APP_SPECIAL_LOG_END = "##logEnd##";

    private LogUtil() {
        throw new UnsupportedOperationException(ExceptionMessageConstant.UN_SUPPORT_INSTANTIATE_MSG);
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
