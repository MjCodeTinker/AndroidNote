package com.mj.android_note.module.plugins;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mj.android_note.R;
import com.mj.lib.base.log.LogUtil;

public class PluginProxyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("mj","PluginProxyActivity#onCreate()");
        setContentView(R.layout.activity_plugin_proxy);
    }
}
