package com.mj.android_note.ui.activity.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceView;
import android.view.View;

import com.mj.android_note.R;
import com.mj.android_note.app.NoteApplication;
import com.mj.android_note.ui.activity.BaseActivity;
import com.mj.android_note.ui.fragment.PermissionFragment;
import com.mj.android_note.utils.ToastUtils;

import java.io.IOException;

/**
 * Author      : MJ
 * Date        : 2018/10/11--15:34
 * Email       : miaojian@conew.com
 * Description : 6.0 动态权限
 */

public class PermissionActivity extends BaseActivity {

    private static final String TAG = "PermissionActivity";
    private SurfaceView surfaceView;
    private static final int REQUEST_PERMISSION = 1000;

    private String fragmentTAG = "tt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        surfaceView = findViewById(R.id.permission_surfaceView);
        findViewById(R.id.permission_btn_open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(fragmentTAG);
                if (fragmentByTag == null) {
                    fragmentByTag = new PermissionFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(fragmentByTag, fragmentTAG)
                            .commitNow();
                }

                if (hasPermission(PermissionActivity.this, Manifest.permission.CAMERA, Manifest.permission.SEND_SMS)) {
                    openCamera();
                } else {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.SEND_SMS}, REQUEST_PERMISSION);
                }
            }
        });
    }




    private void openCamera() {

        Camera camera = Camera.open(0);
        try {
            camera.setPreviewDisplay(surfaceView.getHolder());
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean hasPermission(Context context, String... permission) {
        for (String s : permission) {
            if (ContextCompat.checkSelfPermission(NoteApplication.getInstance(), s) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (permissions.length != 0) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        openCamera();
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, permissions[i])) {
                            ToastUtils.showShortToast("用户拒绝了权限" + permissions[i]);
                        } else {
                            ToastUtils.showShortToast("不在询问，并且拒绝了此权限" + permissions[i]);
                        }

                    }
                }
            }
        }
    }


}
