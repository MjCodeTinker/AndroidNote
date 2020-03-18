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
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mj.android_note.R;
import com.mj.android_note.app.NoteApplication;
import com.mj.android_note.base.AndroidKeyWatcher;
import com.mj.android_note.base.serializable_parcelable.School;
import com.mj.android_note.base.serializable_parcelable.TestSerializableAndParcelable;
import com.mj.android_note.base.serializable_parcelable.User;
import com.mj.android_note.module.plugins.HookManager;
import com.mj.android_note.module.plugins.PluginManager;
import com.mj.android_note.module.plugins.ResourceManager;
import com.mj.android_note.ui.activity.butter_knife.TestButterKnifeActivity;
import com.mj.android_note.utils.ToastUtils;
import com.mj.lib.base.log.LogUtil;
import com.mj.lib.base.thread.ThreadUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
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

    boolean pluginIsLoaded;


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

        LogUtil.e("mj", "external application:" + getApplication());
        setListener(R.id.main_activity_btn_plugin, (v) -> {
//            String apkPath = Environment.getExternalStorageDirectory() + "/plugin_test.apk";

            String apkPath = getFilesDir().getPath() + "/plugin_test.apk";
            ThreadUtils.getInstance().exeDbAction(() -> {
                if (!pluginIsLoaded) {
                    try {

                        InputStream inputStream = getAssets().open("plugin_test-release-unsigned.apk");

                        File file = new File(apkPath);
                        if (file.exists()) file.delete();
                        file.createNewFile();
                        OutputStream outputStream = new FileOutputStream(file);
                        int len;
                        while ((len = inputStream.read()) != -1) {
                            outputStream.write(len);
                        }
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 加载插件中的apk
                    PluginManager.loadPluginApk(MainActivity.this, apkPath);
                    // 加载插件中的资源
                    NoteApplication.getInstance().setPluginResource(ResourceManager.loadPluginResource(MainActivity.this, apkPath));
                    // Hook activity启动流程的intent
                    HookManager.hookStartActivity(MainActivity.this);
                    HookManager.hookHandler();

                    LogUtil.e("mj", "host application:" + getApplication());
                }
                // 启动插件
                Intent intent = new Intent();
                intent.setClassName("com.mj.plugin_test", "com.mj.plugin_test.PluginActivity");
//                intent.setClassName("com.mj.android_note", "com.mj.android_note.module.plugins.PluginProxyActivity");
//                intent.setClassName("com.mj.android_note", "com.mj.android_note.ui.activity.butter_knife.TestButterKnifeActivity");
                startActivity(intent);
                pluginIsLoaded = true;
                // 测试加载插件中的类
//                try {
//                    Class<?> aClass = Class.forName("com.mj.plugin_test.PluginTestClass");
//                    Method showLog = aClass.getDeclaredMethod("showLog");
//                    showLog.setAccessible(true);
//                    showLog.invoke(aClass.newInstance());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            });
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
        testHandlerThread();
        testLruCache();
    }

    // 测试lruCache
    private void testLruCache() {
        // LruCache
        LruCache<Integer, String> lruCache = new LruCache<Integer, String>(2) {
//            @Override
//            protected int sizeOf(Integer key, String value) {
//                return 10 * key;
//            }
        };
        lruCache.put(1, "1");
        lruCache.put(2, "2");
        lruCache.put(3, "3");
        Map<Integer, String> snapshot = lruCache.snapshot();
        for (Map.Entry<Integer, String> integerStringEntry : snapshot.entrySet()) {
            LogUtil.e(TAG, "key : " + integerStringEntry.getKey());
        }
        LogUtil.e(TAG, "lruCache Size = " + lruCache.size());
    }

    private void testHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtil.e("mj", "from :" + msg.obj + "--Thread");
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = Thread.currentThread().getName();
            handler.sendMessage(msg);
        });
        executorService.execute(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = Thread.currentThread().getName();
            handler.sendMessage(msg);
        });
    }


    private void testSharedPreference() {
        SharedPreferences test_sharedPreference = getSharedPreferences("test_sharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor edit = test_sharedPreference.edit();
        edit.putBoolean("", true);
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


    private void setListener(int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }


    private AndroidKeyWatcher androidKeyWatcher;

    private AndroidKeyWatcher getAndroidKeyWatcher() {
        if (androidKeyWatcher == null) {
            androidKeyWatcher = new AndroidKeyWatcher();
        }
        return androidKeyWatcher;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAndroidKeyWatcher().register(this, (result) -> {
            if (result == AndroidKeyWatcher.KeyType.SINGLE_CLICK_HOME) {
                LogUtil.e(TAG, "home 按键被点击 result : " + result);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (androidKeyWatcher != null) {
            androidKeyWatcher.unRegister(this);
        }
    }
}
