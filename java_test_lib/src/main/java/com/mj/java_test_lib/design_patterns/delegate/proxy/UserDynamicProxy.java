package com.mj.java_test_lib.design_patterns.delegate.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Author      : MJ
 * Date        : 2020-02-21--15:23
 * Email       : miaojian_666@126.com
 * Description :
 */
public class UserDynamicProxy implements InvocationHandler {

    // 被代理对象，也就是原始对象
    private Object originObject;

    public UserDynamicProxy(Object originObject) {
        this.originObject = originObject;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("method name : " + method.getName() + "--args : " + Arrays.toString(objects));
        System.out.println("origin object : " + originObject.getClass().getName());
        System.out.println("generate proxy object : " + o.getClass().getName());
        Object returnResult = method.invoke(originObject, objects);
        System.out.println("returnResult: " + returnResult + "--" + returnResult.getClass().getName());
        if (returnResult instanceof String) {
            returnResult = "我是动态代理中的 invoke 方法," + returnResult;
        }
        return returnResult;
    }
}
