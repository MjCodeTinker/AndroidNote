package com.mj.plugin_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

@SuppressLint("Registered")
public class BaseActivity extends Activity {
    @Override
    public Resources getResources() {
        Log.e("mj", "BaseActivity#getResources()---getApplication():" + getApplication());
        if (getApplication() != null && getApplication().getResources() != null) {
            return getApplication().getResources();
        } else {
            return super.getResources();
        }
    }
}
