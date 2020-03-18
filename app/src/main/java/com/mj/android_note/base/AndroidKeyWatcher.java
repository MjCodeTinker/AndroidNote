package com.mj.android_note.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Author      : MJ
 * Date        : 2020-03-18--17:32
 * Email       : miaojian_666@126.com
 * Description : Android系统按键监听
 * ！！！！！重要，注册了监听事件一定要在合适的时机解除注册！！！！！！
 * 比如在activity的 onResume 添加了监听，在对应的 onPause  去做解除注册
 * 没必要做成单利，尽量少用单利
 */
public class AndroidKeyWatcher {

    /**
     * 按键监听器
     */
    private IKeyWatcher iKeyWatcher;

    // 安卓按键监听广播接收器
    private KeyReceiver keyReceiver;

    /**
     * 注册按键监听器
     *
     * @param context     context
     * @param iKeyWatcher 监听器
     */
    public void register(Context context, IKeyWatcher iKeyWatcher) {
        this.iKeyWatcher = iKeyWatcher;
        registerReceiver(context);
    }

    /**
     * 解除注册
     *
     * @param context context
     */
    public void unRegister(Context context) {
        this.iKeyWatcher = null;
        unRegisterReceiver(context);
    }

    /**
     * 注册广播
     *
     * @param context context
     */
    private void registerReceiver(Context context) {
        if (keyReceiver == null) {
            keyReceiver = new KeyReceiver();
        }
        // 使用applicationContext，是为了防止activity的内存泄漏
        if (context != null && context.getApplicationContext() != null) {
            IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            context.getApplicationContext().registerReceiver(keyReceiver, filter);
        }
    }

    private void unRegisterReceiver(Context context) {
        if (context != null && context.getApplicationContext() != null && keyReceiver != null) {
            context.getApplicationContext().unregisterReceiver(keyReceiver);
        }
    }


    /**
     * 按键广播接收器
     */
    private class KeyReceiver extends BroadcastReceiver {
        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                    // 短按Home键
                    handleListener(KeyType.SINGLE_CLICK_HOME);
                } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                    // 长按Home键 或者 activity切换键
                    handleListener(KeyType.LONG_CLICK_HOME);
                }
            }
        }

        /**
         * 处理监听器
         *
         * @param keyType 按键类型
         */
        private void handleListener(KeyType keyType) {
            if (iKeyWatcher != null) {
                iKeyWatcher.onChange(keyType);
            }
        }
    }


    /**
     * 系统按键类型
     */
    public enum KeyType {
        // 单次点击home按键
        SINGLE_CLICK_HOME,
        // 长按 home按键
        LONG_CLICK_HOME
    }

    /**
     * 按键监听
     */
    public interface IKeyWatcher {
        /**
         * 通知改变
         *
         * @param keyType 按键类型
         */
        void onChange(KeyType keyType);
    }

}
