package com.mj.lib.base.physical_storage;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;

/**
 * Author      : MJ
 * Date        : 2019/3/8--17:32
 * Email       : miaojian@conew.com
 * Description : 路径相关的工具类
 */

public class AppPathUtils {


    private AppPathUtils() {
        throw new UnsupportedOperationException(" Can`t instantiation me");
    }

    private static Context applicationContext;

    /**
     * 建议在application 入口处调用 初始化
     *
     * @param context context
     */
    public static void init(Context context) {
        applicationContext = context.getApplicationContext();
    }


    /**
     * @return 获取app私有路径 data/data/packageName/
     */
    public static String getAppPrivatePath() {
        return subAppPrivatePathsDir(applicationContext.getFilesDir().getPath()) + File.separator;
    }

    /**
     * @return 获取app私有file路径 data/data/packageName/files/
     */
    public static String getAppPrivateFilesPath() {
        return applicationContext.getFilesDir().getPath() + File.separator;
    }

    /**
     * @return 获取app私有cache路径 data/data/packageName/caches/
     */
    public static String getAppPrivateCachesPath() {
        return applicationContext.getCacheDir().getPath() + File.separator;
    }

    /**
     * 截取app私有目录
     *
     * @param path data/data/packageName/files
     * @return data/data/packageName/files
     */
    private static String subAppPrivatePathsDir(String path) {
        if (TextUtils.isEmpty(path)) {
            return path;
        }
        int i = path.lastIndexOf("/");
        return path.substring(0, i);
    }

}
