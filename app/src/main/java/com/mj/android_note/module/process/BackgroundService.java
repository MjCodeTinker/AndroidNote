package com.mj.android_note.module.process;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2020-03-19--21:23
 * Email       : miaojian_666@126.com
 * Description : 后台服务
 */
public class BackgroundService extends Service {


    private static final String TAG = "BackgroundService";
    private SerViceBinder serViceBinder = new SerViceBinder();

    // binder 服务
    class SerViceBinder extends Binder {
        BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e(ProcessConstants.TAG, " ---onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e(ProcessConstants.TAG, "onBind : intent = " + intent);
        return serViceBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e(ProcessConstants.TAG, "onStartCommand intent: " + intent + "--flags = " + flags + "--startId:" + startId);
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.e(ProcessConstants.TAG, "onUnbind intent:" + intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(ProcessConstants.TAG, "onDestroy");
    }


    public int add(int i, int j) {
        return i + j;
    }

}
