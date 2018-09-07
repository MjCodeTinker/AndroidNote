package com.mj.android_note.app;

import com.mj.android_note.BuildConfig;
import com.mj.android_note.utils.constant.ExceptionMessageConstant;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : app配置信息
 */
public final class AppConfig {

    private AppConfig() {
        throw new UnsupportedOperationException(ExceptionMessageConstant.UN_SUPPORT_INSTANTIATE_MSG);
    }

    /**
     * app环境配置
     */
    public final static class AppEnv {
        public static final boolean isDebug = BuildConfig.ENV_DEBUG;//是否为debug模式
    }

    /**
     * 数据库配置
     */
    public final static class DbConfig {
        public static final String DB_NAME = "note.db";//数据库名称
        /**
         * 数据库版本 之后每一个版本都要在此加入说明
         * --1 .第一个版本只有文件夹表
         */
        public static final int DB_VERSION = 1;
        public static final String DB_TABLE_NAME_FOLDER = "folder";//文件夹表
    }

}
