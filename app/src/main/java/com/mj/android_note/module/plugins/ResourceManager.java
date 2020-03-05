package com.mj.android_note.module.plugins;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ResourceManager {


    public static Resources loadPluginResource(Context context, String apkPath) {
        try {
            AssetManager pluginAssetManager = AssetManager.class.newInstance();
            // 设置apkPath
            Method addAssetPath = pluginAssetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(pluginAssetManager, apkPath);
            Resources hostResource = context.getResources();
            return new Resources(pluginAssetManager, hostResource.getDisplayMetrics(), hostResource.getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
