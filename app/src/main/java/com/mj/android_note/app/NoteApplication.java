package com.mj.android_note.app;

import android.app.Application;

import androidx.core.graphics.PathUtils;

import com.mj.android_note.utils.LocalResourceUtil;
import com.mj.lib.base.physical_storage.AppPathUtils;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : app的入口Application
 */
public class NoteApplication extends Application {
    private static NoteApplication instance;

    public static NoteApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppPathUtils.init(this);
    }
}
