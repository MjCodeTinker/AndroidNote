package com.mj.android_note.app;

import android.app.Application;

import com.mj.android_note.utils.LocalResourceUtil;

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
        LocalResourceUtil.init(this);
    }
}
