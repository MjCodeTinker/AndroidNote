package com.mj.lib.base.communication.app_inner;

import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.lib.base.communication.app_inner.entity.MethodInfo;
import com.mj.lib.base.communication.app_inner.type.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2019/3/4--14:04
 * Email       : miaojian@conew.com
 * Description : 模仿eventBus 进行app组件内通信
 */

public class MessageTrain {

    private static volatile MessageTrain instance;

    // 所有回调方法集合与对象的映射关系
    private Map<Object, List<MethodInfo>> registers;

    private MessageTrain() {
        registers = new HashMap<>();
    }

    public static MessageTrain getDefault() {
        if (instance == null) {
            synchronized (MessageTrain.class) {
                if (instance == null) {
                    instance = new MessageTrain();
                }
            }
        }
        return instance;
    }


    public void post(Object message) {
        List<MethodInfo> methodInfoList = registers.get(message);
        if (methodInfoList != null) {
            registers.remove(message);
        }
    }

    /**
     * 注册
     *
     * @param obj 类对象，Activity Fragment...
     */
    public void register(Object obj) {
        List<MethodInfo> methodInfoList = registers.get(obj);
        if (methodInfoList == null) {
            // 说明还没有注册，开始执行注册的操作
            methodInfoList = findAllMessageTrainMethodInfo(obj);
            registers.put(obj, methodInfoList);
        }
    }

    public void unRegister(Object obj) {

    }


    /**
     * 查找某个类中包含 subscriber注解的方法
     *
     * @param obj obj
     * @return 方法信息
     */
    private List<MethodInfo> findAllMessageTrainMethodInfo(Object obj) {
        List<MethodInfo> methodInfoList = new ArrayList<>();
        Class<?> cls = obj.getClass();

        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            Subscriber subscriber = method.getAnnotation(Subscriber.class);
            if (subscriber == null) {
                continue;
            }
            ThreadMode threadMode = subscriber.threadMode();
            Class<?>[] parameterTypes = method.getParameterTypes();
            // 只接受一个参数的
            if (parameterTypes.length == 1) {
                Class<?> parameterType = parameterTypes[0];
                MethodInfo methodInfo = new MethodInfo(parameterType, threadMode, method);
                methodInfoList.add(methodInfo);
            }
        }
        return methodInfoList;
    }


}
