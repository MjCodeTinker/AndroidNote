package com.mj.plugin_test;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Override
    public Resources getResources() {
        return getApplication().getResources();
    }
}
