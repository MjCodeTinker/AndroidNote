package com.mj.android_note.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mj.android_note.R;
import com.mj.android_note.libary.adaptive.StartSystemSettingPageImpl;
import com.mj.android_note.libary.adaptive.SystemSettingPageEntity;
import com.mj.android_note.ui.activity.db.DbMainActivity;
import com.mj.android_note.utils.LogUtil;

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

        findViewById(R.id.main_activity_btn_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbMainActivity.class);
                startActivity(intent);
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

    }

    private Intent xiaomiApi(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (hasActivity(context, intent)) return intent;

        intent.setPackage("com.miui.securitycenter");
        if (hasActivity(context, intent)) return intent;

        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        return intent;
    }

    private boolean hasActivity(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e(TAG, "requestCode=" + requestCode + "---resultCode=" + resultCode + "--" + data);
    }

}
