package com.mj.android_note.module.plugins;

import android.content.Context;

import com.mj.lib.base.log.LogUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Author      : MJ
 * Date        : 2020-03-04--15:47
 * Email       : miaojian_666@126.com
 * Description : 插件加载器
 */
public class PluginManager {


    /**
     * 加载插件Apk
     *
     * @param context 宿主context
     * @param apkPath apk路径
     */
    public static void loadPluginApk(Context context, String apkPath) {


        try {
            // 1.拿到DexPathList 在 BaseDexClassLoader 中的字段
            Class<?> baseClassLoader = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseClassLoader.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            // 2.拿到宿主的DexPathList,并且拿到对应的dexElements数组
            PathClassLoader hostPathClassLoader = (PathClassLoader) context.getClassLoader();
            Object hostDexPathListObj = pathListField.get(hostPathClassLoader);
            Class<?> hostDexPathListClass = hostDexPathListObj.getClass();
            Field hostDexElementsField = hostDexPathListClass.getDeclaredField("dexElements");
            hostDexElementsField.setAccessible(true);
            Object[] hostDexElementsObj = (Object[]) hostDexElementsField.get(hostDexPathListObj);
            LogUtil.e("mj", "hostDexElementsObj.length = " + hostDexElementsObj.length);
            // 3.拿到插件的DexPathList,并且拿到对应的dexElements数组
            DexClassLoader pluginDexClassLoader = new DexClassLoader(
                    apkPath,
                    context.getCacheDir().getAbsolutePath(),
                    null,
                    context.getClassLoader());
            Object pluginDexPathListObj = pathListField.get(pluginDexClassLoader);
            Class<?> pluginDexPathListClass = pluginDexPathListObj.getClass();
            Field pluginDexElementsField = pluginDexPathListClass.getDeclaredField("dexElements");
            pluginDexElementsField.setAccessible(true);
            Object[] pluginDexElementsObj = (Object[]) pluginDexElementsField.get(pluginDexPathListObj);

            // 4.将宿主的dexElements 与插件的dexElements进行合并
            Object[] newDexElements = (Object[]) Array.newInstance(
                    hostDexElementsObj.getClass().getComponentType(),
                    hostDexElementsObj.length + pluginDexElementsObj.length);

            System.arraycopy(hostDexElementsObj, 0, newDexElements, 0, hostDexElementsObj.length);
            System.arraycopy(pluginDexElementsObj, 0, newDexElements, hostDexElementsObj.length, pluginDexElementsObj.length);

            // 5.把合并后的数组设置给宿主
            hostDexElementsField.set(hostDexPathListObj, newDexElements);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
