package com.mj.android_note.module.plugins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

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

            Object proxyObj = Proxy.newProxyInstance(
                    context.getClassLoader(),
                    new Class[]{iActivityManagerObj.getClass()},
                    (proxy, method, args) -> {
                        // 将插件的intent 替换为预制的intent
                        if (method.getName().equals("startActivity")) {
                            int intentIndex = 0;
                            for (int i = 0; i < args.length; i++) {
                                if (args[i].getClass().getName().equals("Intent")) {
                                    intentIndex = i;
                                    break;
                                }
                            }
                            Intent pluginIntent = (Intent) args[intentIndex];
                            Intent proxyIntent = new Intent();
                            proxyIntent.putExtra(PLUGIN_INTENT, pluginIntent);
                            proxyIntent.setClassName("com.mj.android_note", "com.mj.android_note.module.plugins.PluginProxyActivity");
                            args[intentIndex] = proxyIntent;
                            return method.invoke(iActivityManagerObj, args);
                        }
                        return null;
                    });
            singletonInstanceField.set(iActivityManagerObj, proxyObj);
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
                        Field intentField = msg.obj.getClass().getDeclaredField("intent");
                        intentField.setAccessible(true);
                        Intent proxyIntent = (Intent) intentField.get(msg.obj);
                        Intent pluginIntent = proxyIntent.getParcelableExtra(PLUGIN_INTENT);
                        proxyIntent.setComponent(pluginIntent.getComponent());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return true;
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
