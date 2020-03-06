package com.mj.plugin_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PluginActivity extends BaseActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("mj", "我是插件Activity#onCreate()");
        setContentView(R.layout.activity_plugin);
        TextView tv = findViewById(R.id.plugin_activity_tv);
        tv.setText(getResources().getString(R.string.app_name) + "-- 我是插件Activity");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
