package com.mj.lib.base.communication.app_inner.entity;

import com.mj.lib.base.communication.app_inner.type.ThreadMode;

import java.lang.reflect.Method;

/**
 * Author      : MJ
 * Date        : 2019/3/4--14:26
 * Email       : miaojian@conew.com
 * Description : messageTrain 方法信息
 */

public class MethodInfo {

    // 方法参数
    private Class<?> params;
    // 线程模型
    private ThreadMode threadMode;
    // 方法名称
    private Method method;


    public MethodInfo(Class<?> params, ThreadMode threadMode, Method method) {
        this.params = params;
        this.threadMode = threadMode;
        this.method = method;
    }


    public Class<?> getParams() {
        return params;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public Method getMethod() {
        return method;
    }

}
