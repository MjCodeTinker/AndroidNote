package com.mj.android_note.app;

import android.app.Application;
import android.content.res.Resources;

import com.mj.android_note.module.plugins.ResourceManager;
import com.mj.lib.base.physical_storage.AppPathUtils;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : app的入口Application
 */
public class NoteApplication extends Application {
    private static NoteApplication instance;

    // 插件Resource
    private Resources pluginResource;

    public static NoteApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppPathUtils.init(this);
    }

    public void setPluginResource(Resources resource) {
        this.pluginResource = resource;
    }

    @Override
    public Resources getResources() {
        return pluginResource == null ? super.getResources() : pluginResource;
    }
}
