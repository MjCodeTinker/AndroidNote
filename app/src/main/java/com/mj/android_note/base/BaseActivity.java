package com.mj.android_note.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Author      : MJ
 * Date        : 2018/12/20--16:30
 * Email       : miaojian@conew.com
 * Description : activity 的基类
 */

@SuppressLint("Registered")
public class BaseActivity extends FragmentActivity {

    /**
     * 启动Activity,无bundle
     */
    protected static void startAct(Context context, Class<? extends Activity> cls) {
        startAct(context, cls, null);
    }

    /**
     * 启动Activity,有bundle
     */
    protected static void startAct(Context context, Class<? extends Activity> cls, Bundle bundle) {
        context.startActivity(buildIntent(context, cls, bundle));
    }

    /**
     * 启动Activity 增加 requestCode,无bundle
     */
    protected void startActForResult(Context context, Class<? extends Activity> cls, int requestCode) {
        startActForResult(context, cls, requestCode, null);
    }

    /**
     * 启动Activity 增加 requestCode，有bundle
     */
    protected void startActForResult(Context context, Class<? extends Activity> cls, int requestCode, Bundle bundle) {
        startActivityForResult(buildIntent(context, cls, bundle), requestCode);
    }

    /**
     * 构建 intent
     *
     * @param context context
     * @param cls     要启动的activity
     * @param bundle  bundle
     * @return intent
     */
    @NonNull
    private static Intent buildIntent(Context context, Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }


}
