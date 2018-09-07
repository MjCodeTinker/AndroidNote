package com.mj.android_note.utils.constant;

/**
 * Author      : MJ
 * Date        : 2018/9/6--下午9:45
 * Email       : miaojian_666@126.com
 * Description : 异常信息
 */
public class ExceptionMessageConstant {
    private ExceptionMessageConstant() {
        throw new UnsupportedOperationException(UN_SUPPORT_INSTANTIATE_MSG);
    }

    //不支持类实例化message
    public static final String UN_SUPPORT_INSTANTIATE_MSG = "you can`t instantiate me...";
    //没有初始化抛出的空指针message
    public static final String NULL_POINTER_INIT_MSG = "please init me...";
}
