package com.mj.android_note.utils;

import android.app.Application;
import android.content.res.Resources;

import com.mj.android_note.app.NoteApplication;
import com.mj.android_note.utils.constant.ExceptionMessageConstant;

/**
 * Author      : MJ
 * Date        : 2018/9/6--下午10:34
 * Email       : miaojian_666@126.com
 * Description : resource 工具类
 */
public class LocalResourceUtil {

    private LocalResourceUtil() {
        throw new UnsupportedOperationException(ExceptionMessageConstant.UN_SUPPORT_INSTANTIATE_MSG);
    }

    private static Application application;

    public static void init(Application application) {
        LocalResourceUtil.application = application;
    }

    // 获取resource
    public static Resources getResource() {
        checkInitFinish();
        return application.getResources();
    }

    /**
     * 从资源文件中获取字符串
     *
     * @param resId 资源id
     * @return 字符串
     */
    public static String getStr(int resId) {
        checkInitFinish();
        return getResource().getString(resId);
    }


    // 检查是否初始化完成
    private static void checkInitFinish() {
        if (application == null) {
            throw new NullPointerException(ExceptionMessageConstant.NULL_POINTER_INIT_MSG);
        }
    }
}
