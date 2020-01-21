package com.mj.android_note.ui.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mj.android_note.R;
import com.mj.android_note.base.serializable_parcelable.School;
import com.mj.android_note.base.serializable_parcelable.TestSerializableAndParcelable;
import com.mj.android_note.base.serializable_parcelable.User;
import com.mj.android_note.ui.activity.butter_knife.TestButterKnifeActivity;
import com.mj.lib.base.log.LogUtil;
import com.mj.lib.base.thread.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

//        MessageTrain.getDefault().register(this);

//        final LottieAnimationView lottieView = findViewById(R.id.lottieView);
//        lottieView.setImageAssetsFolder("images/");
//        lottieView.setImageAssetsFolder("/");
//        lottieView.setImageAssetsFolder();
//        lottieView.setAnimation("data.json");
//        lottieView.loop(true);

        findViewById(R.id.main_activity_btn_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DbMainActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, TestButterKnifeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("parcelable_test", new School(100, "北京大学", "北京市海淀区"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
        testHandler();
        testSerializable();
        testSharedPreference();
    }

    private void testSharedPreference() {
        SharedPreferences test_sharedPreference = getSharedPreferences("test_sharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor edit = test_sharedPreference.edit();
        edit.putBoolean("",true);
        edit.apply();
        boolean commit = edit.commit();
    }

    private void testSerializable() {

        // 测试serializable
        TestSerializableAndParcelable testSerializableAndParcelable = new TestSerializableAndParcelable();
        User u = new User();
        u.name = "Mj";
        u.setAge(22);
        u.password = "123456";
        User.email = "miaojian@cmcm.com";

        ThreadUtils.getInstance().exeDbAction(() -> {
            testSerializableAndParcelable.serializableUser(u);

            ThreadUtils.getInstance().postUiThread(() -> {
                User user = testSerializableAndParcelable.deSerializableUser();
                LogUtil.e("mj", "u = " + user);
                LogUtil.e("mj", "对象是否相等：" + (u == user));
            });
        });

    }

    Handler handler;

    // handler测试
    @SuppressLint("HandlerLeak")
    private void testHandler() {

        LogUtil.e("mj", "testHandler被调用");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            Looper.prepare();
            Looper looper = Looper.myLooper();
            handler = new Handler(looper) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    LogUtil.i("mj", "我收到消息了" + msg.what);
                }
            };
            // 必须要在创建handler 之后调用 looper.loop
            Looper.loop();
        });
        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);
        });
    }

    private void testObjectAnimator() {
        int[] colorChangeArray = {
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorAccent)};
        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(getWindow(), "statusBarColor", colorChangeArray);
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
