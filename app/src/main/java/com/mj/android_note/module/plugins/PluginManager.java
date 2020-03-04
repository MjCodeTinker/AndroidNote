package com.mj.android_note.module.plugins;

import android.content.Context;

import dalvik.system.DexClassLoader;

/**
 * Author      : MJ
 * Date        : 2020-03-04--15:47
 * Email       : miaojian_666@126.com
 * Description : 插件加载器
 */
public class PluginManager {


    public static void loadApk(Context context, String apkPath) {
        // 加载插件class到宿主
        loadClassToMainApk(context, apkPath);
        // 加载插件的Resource到宿主
        loadResourceToMainApk(context, apkPath);
        // hook宿主中的Intent
        hook();
    }

    private static void hook() {

    }

    private static void loadResourceToMainApk(Context context, String apkPath) {
        DexClassLoader dexClassLoader = new DexClassLoader(
                apkPath,
                context.getCacheDir().getAbsolutePath(),
                null,
                context.getClassLoader());
    }

    private static void loadClassToMainApk(Context context, String apkPath) {

    }

}
