package com.mj.lib.base.communication.app_inner;

import android.os.Handler;
import android.os.Looper;
import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.lib.base.communication.app_inner.entity.MethodInfo;
import com.mj.lib.base.communication.app_inner.type.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    // 主线程handler
    private Handler mainHandler;

    // 线程池
    private ExecutorService executorService;

    private MessageTrain() {
        registers = new HashMap<>();
        mainHandler = new Handler(Looper.getMainLooper());
        executorService = Executors.newSingleThreadExecutor();
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


    /**
     * 发送消息
     *
     * @param message 要发送的消息
     */
    public void post(final Object message) {

        Set<Object> keySet = registers.keySet();
        Iterator<Object> iterator = keySet.iterator();

        if (iterator.hasNext()) {
            final Object subscriberClass = iterator.next();
            List<MethodInfo> methodInfoList = registers.get(message);
            for (MethodInfo methodInfo : methodInfoList) {

                final Method method = methodInfo.getMethod();
                final Class<?> params = methodInfo.getParams();
                ThreadMode threadMode = methodInfo.getThreadMode();
                // 判断参数 是否一样（包含父类 父接口）
                if (params.isAssignableFrom(message.getClass())) {

                    if (ThreadMode.MAIN == threadMode) {

                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            postMessage(method, subscriberClass, message);
                        } else {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    postMessage(method, subscriberClass, message);
                                }
                            });
                        }

                    } else if (ThreadMode.WORK_THREAD == threadMode) {

                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    postMessage(method, subscriberClass, message);
                                }
                            });
                        } else {
                            postMessage(method, subscriberClass, message);
                        }

                    }
                }

            }
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

    /**
     * 接触注册
     *
     * @param obj 那个类
     */
    public void unRegister(Object obj) {
        List<MethodInfo> methodInfoList = registers.get(obj);
        if (methodInfoList != null) {
            registers.remove(obj);
        }
    }


    /**
     * 调用注册注册改消息的方法
     *
     * @param method  method
     * @param message 参数
     */
    private void postMessage(Method method, Object subscriberClass, Object message) {
        try {
            method.invoke(subscriberClass, message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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
            } else {
                throw new RuntimeException("only allow has parameter");
            }
        }
        return methodInfoList;
    }


}
