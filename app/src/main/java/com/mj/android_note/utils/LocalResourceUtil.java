package com.mj.android_note.utils;

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

    // 获取resource
    public static Resources getResource() {
        return NoteApplication.getInstance().getResources();
    }

    /**
     * 从资源文件中获取字符串
     *
     * @param resId 资源id
     * @return 字符串
     */
    public static String getStr(int resId) {
        return getResource().getString(resId);
    }

    /**
     * 从资源文件中获取color
     * @param resId 资源id
     * @return 颜色
     */
    public static int getColor(int resId){
        return getResource().getColor(resId);
    }


}
