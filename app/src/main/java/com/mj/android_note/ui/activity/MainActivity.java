package com.mj.android_note.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.mj.android_note.R;
import com.mj.android_note.libary.adaptive.StartSystemSettingPageImpl;
import com.mj.android_note.libary.adaptive.SystemSettingPageEntity;
import com.mj.android_note.module.process.MainProcessActivity;
import com.mj.android_note.ui.activity.db.DbMainActivity;
import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.lib.base.communication.app_inner.type.ThreadMode;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 应用的主界面
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final LottieAnimationView lottieView = findViewById(R.id.lottieView);
//        lottieView.setImageAssetsFolder("images/");
//        lottieView.setImageAssetsFolder("/");
//        lottieView.setImageAssetsFolder();
//        lottieView.setAnimation("data.json");
//        lottieView.loop(true);

        findViewById(R.id.main_activity_btn_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbMainActivity.class);
                startActivity(intent);
            }
        });
        lottieView.setImageAssetsFolder("lottie/images/");
        Cancellable cancellable = LottieComposition.Factory.fromAssetFileName(this, "lottie/data.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                if (composition != null) {
                    lottieView.setComposition(composition);
                }

                lottieView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lottieView.playAnimation();
                        Log.d(TAG,"duration="+lottieView.getDuration());
                    }
                }, 2000);
            }
        });




        findViewById(R.id.main_activity_btn_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemSettingPageEntity adaptationEntity = new SystemSettingPageEntity();
                adaptationEntity.setContext(MainActivity.this);
                new StartSystemSettingPageImpl().beganToFit(adaptationEntity);
            }
        });


        findViewById(R.id.main_activity_btn_process_communication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainProcessActivity.launcher(MainActivity.this);
            }
        });


    }



    @Subscriber(threadMode = ThreadMode.MAIN)
    public void getMessage(Message message){

    }

}
