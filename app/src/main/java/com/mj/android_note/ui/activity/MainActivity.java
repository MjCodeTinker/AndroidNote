package com.mj.android_note.ui.activity;

import android.animation.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import com.mj.android_note.R;
import com.mj.lib.base.communication.app_inner.MessageTrain;
import com.mj.lib.base.log.LogUtil;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 应用的主界面
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    ValueAnimator valueAnimator;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageTrain.getDefault().register(this);

//        final LottieAnimationView lottieView = findViewById(R.id.lottieView);
//        lottieView.setImageAssetsFolder("images/");
//        lottieView.setImageAssetsFolder("/");
//        lottieView.setImageAssetsFolder();
//        lottieView.setAnimation("data.json");
//        lottieView.loop(true);

//        findViewById(R.id.main_activity_btn_db).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DbMainActivity.class);
//                startActivity(intent);
//            }
//        });
//        lottieView.setImageAssetsFolder("lottie/images/");
//        Cancellable cancellable = LottieComposition.Factory.fromAssetFileName(this, "lottie/data.json", new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(@Nullable LottieComposition composition) {
//                if (composition != null) {
//                    lottieView.setComposition(composition);
//                }
//
//                lottieView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        lottieView.playAnimation();
//                        Log.d(TAG, "duration=" + lottieView.getDuration());
//                    }
//                }, 2000);
//            }
//        });

//        findViewById(R.id.main_activity_btn_permission).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SystemSettingPageEntity adaptationEntity = new SystemSettingPageEntity();
//                adaptationEntity.setContext(MainActivity.this);
//                new StartSystemSettingPageImpl().beganToFit(adaptationEntity);
//            }
//        });

//        findViewById(R.id.main_activity_btn_process_communication).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainProcessActivity.launcher(MainActivity.this);
//            }
//        });
//
//        final Button btnView = findViewById(R.id.main_activity_btn_dispatch_touch);
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                valueAnimator.start();
////                DispatchTouchActivity.launcher(MainActivity.this);
//            }
//        });

        testValueAnimator();
        testObjectAnimator();
    }

    private void testObjectAnimator() {
        int [] colorChangeArray = {
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorAccent)};
        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(getWindow(),"statusBarColor", colorChangeArray);
        View btnObjAnim = findViewById(R.id.main_activity_btn_obj_anim_test);
        btnObjAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
            }
        });
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setDuration(5000);
    }

    private void testValueAnimator() {
        final TextView btnAnimView = findViewById(R.id.main_activity_btn_anim_test);

        btnAnimView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
            }
        });

        final XYTranslation xyTranslationStart = new XYTranslation();
        btnAnimView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xyTranslationStart.x = btnAnimView.getWidth();
                xyTranslationStart.y = btnAnimView.getHeight();
                btnAnimView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        XYTranslation xyTranslationEnd = new XYTranslation();
        xyTranslationEnd.x = 700;
        xyTranslationEnd.y = 350;

        TypeEvaluator<XYTranslation> xyTranslationTypeEvaluator = new TypeEvaluator<XYTranslation>() {
            @Override
            public XYTranslation evaluate(float fraction, XYTranslation startValue, XYTranslation endValue) {
                LogUtil.d(TAG, "fraction = " + fraction);
                XYTranslation xyTranslation = new XYTranslation();
                xyTranslation.x = (int) (startValue.x + (endValue.x - startValue.x) * fraction);
                xyTranslation.y = (int) (startValue.y + (endValue.y - startValue.y) * fraction);
                return xyTranslation;
            }
        };

        valueAnimator = ValueAnimator.ofObject(xyTranslationTypeEvaluator, xyTranslationStart, xyTranslationEnd);
        valueAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                LogUtil.d(TAG, "input = " + input);
                return input;
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                XYTranslation animatedValue = (XYTranslation) animation.getAnimatedValue();
                LogUtil.d(TAG, "animatedValue x = " + animatedValue.x + "-- animatedValueY = " + animatedValue.y);
                btnAnimView.getLayoutParams().width = animatedValue.x;
                btnAnimView.getLayoutParams().height = animatedValue.y;
                btnAnimView.requestLayout();
            }
        });
        valueAnimator.setDuration(1000);
    }

    class XYTranslation {
        int x;
        int y;
    }


}
