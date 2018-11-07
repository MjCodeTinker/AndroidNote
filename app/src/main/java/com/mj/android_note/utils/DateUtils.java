package com.mj.android_note.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

/**
 * Author      : MJ
 * Date        : 2018/11/7--下午11:22
 * Email       : miaojian_666@126.com
 * Description : 日期相关的
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {
    /**
     * 年月日时分
     */
    public static final String YMD_HS = "YYYY-MM-DD HH:SS";

    /**
     * 时间戳转化成字符串
     *
     * @param timeStamp 时间戳
     * @return 字符串
     */
    public static String convertTimeStampToStr(long timeStamp, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(timeStamp);
    }
}
