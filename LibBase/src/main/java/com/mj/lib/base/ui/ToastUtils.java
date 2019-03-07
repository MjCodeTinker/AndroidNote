package com.mj.lib.base.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午11:10
 * Email       : miaojian_666@126.com
 * Description : toast工具类
 */
public class ToastUtils {

    private static final String TAG = "ToastUtils";
    private static Toast mToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("you can`t instantiation me...");
    }

    /**
     * 展示时间较短的toast
     *
     * @param msg 提示信息字符串
     */
    public static void showShortToast(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            Log.d(TAG, "msg not nulls allowed...");
            return;
        }
        createToast(context);
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 创建Toast
     *
     * @param context context文本
     */
    @SuppressLint("ShowToast")
    private static void createToast(Context context) {
        if (null == mToast) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }
}
