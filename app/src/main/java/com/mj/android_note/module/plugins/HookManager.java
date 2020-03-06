package com.mj.android_note.module.plugins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.mj.lib.base.log.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class HookManager {

    private static final String PLUGIN_INTENT = "plugin_intent";


    public static void hookStartActivity(Context context) {

        try {
            // 拿到ActivityManager 对象
            Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
            Field iActivityManagerSingletonField = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            Object iActivityManagerSingletonObj = iActivityManagerSingletonField.get(activityManagerClass);
            // 拿到单利
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field singletonInstanceField = singletonClass.getDeclaredField("mInstance");
            singletonInstanceField.setAccessible(true);
            Object iActivityManagerObj = singletonInstanceField.get(iActivityManagerSingletonObj);

            for (int i = 0; i < iActivityManagerObj.getClass().getInterfaces().length; i++) {
                LogUtil.e("mj", iActivityManagerObj.getClass().getInterfaces()[i] + "");
            }

            Object proxyObj = Proxy.newProxyInstance(
                    context.getClassLoader(),
                    iActivityManagerObj.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        // 将插件的intent 替换为预制的intent
                        try {
                            if (method.getName().equals("startActivity")) {
                                LogUtil.e("mj", "i am iActivityManagerObj InvocationHandler:method = " + method + "--proxy=" + proxy + "--args=" + Arrays.toString(args));
                                int intentIndex = 0;
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] instanceof Intent) {
                                        intentIndex = i;
                                        break;
                                    }
                                }
                                Intent pluginIntent = (Intent) args[intentIndex];
                                Intent proxyIntent = new Intent();
                                proxyIntent.putExtra(PLUGIN_INTENT, pluginIntent);
                                proxyIntent.setClassName("com.mj.android_note", "com.mj.android_note.module.plugins.PluginProxyActivity");
                                args[intentIndex] = proxyIntent;
                                LogUtil.e("mj", "Proxy end.." + args[intentIndex]);
                                Object invoke = method.invoke(iActivityManagerObj, args);
                                LogUtil.e("mj", "invoke = " + invoke + "--Proxy end.." + args[intentIndex]);
                                return invoke;
                            }
                        } catch (Exception e) {
                            LogUtil.e("mj", "e=" + e);
                        }
                        return method.invoke(iActivityManagerObj, args);
                    });
            LogUtil.e("mj", "proxyObj : " + proxyObj + "--iActivityManagerSingletonObj:" + iActivityManagerSingletonObj);
            singletonInstanceField.set(iActivityManagerSingletonObj, proxyObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void hookHandler() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object activityThreadObj = sCurrentActivityThreadField.get(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mHHandler = (Handler) mHField.get(activityThreadObj);

            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mHHandler, (Handler.Callback) msg -> {
                if (msg.what == 100) {
                    try {
                        LogUtil.e("mj", "msg.obj=" + msg.obj);
                        Field intentField = msg.obj.getClass().getDeclaredField("intent");
                        intentField.setAccessible(true);
                        Intent proxyIntent = (Intent) intentField.get(msg.obj);
                        Intent pluginIntent = proxyIntent.getParcelableExtra(PLUGIN_INTENT);
//                        proxyIntent.setComponent(pluginIntent.getComponent());
                        intentField.set(msg.obj, pluginIntent);
                        LogUtil.e("mj", "Callback end intent:" + pluginIntent);
                        LogUtil.e("mj", "Callback end proxyIntent:" + proxyIntent);
                        LogUtil.e("mj", "Callback end:" + msg.obj);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
