package com.mj.lib.base.communication.app_inner.annotation;

import com.mj.lib.base.communication.app_inner.type.ThreadMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author      : MJ
 * Date        : 2019/3/4--14:06
 * Email       : miaojian@conew.com
 * Description : 用于当做app内消息通讯的标识，方便反射查找到对应的回调方法
 */

@Retention(RetentionPolicy.RUNTIME) // 运行时存在的注解
@Target(ElementType.METHOD) //该注解用在方法上
public @interface Subscriber {

    /**
     * 获取线程模型，是否在主线程
     *
     * @return {@link ThreadMode}
     */
    ThreadMode threadMode() default ThreadMode.MAIN;

}
