package com.mj.android_note.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mj.android_note.R;
import com.mj.android_note.utils.constant.BaseConstant;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : app启动页
 */
public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        View parentView = findViewById(R.id.launcher_parent_view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(parentView, BaseConstant.AnimatorPropertyName.ALPHA, 0f, 1f, 0f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startAppMain();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        objectAnimator.start();
    }

    private void startAppMain() {
        Intent intent = new Intent();
        intent.setClass(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
