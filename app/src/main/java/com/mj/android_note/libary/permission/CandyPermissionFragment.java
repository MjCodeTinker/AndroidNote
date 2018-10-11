package com.mj.android_note.libary.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * Author      : MJ
 * Date        : 2018/10/11--19:28
 * Email       : miaojian@conew.com
 * Description : 申请权限的fragment
 */

public class CandyPermissionFragment extends Fragment {

    // 权限的请求码
    private static final int REQUEST_PERMISSION_CODE = 1000;

    // activity
    private Activity activity;

    public static CandyPermissionFragment newInstance() {
        return new CandyPermissionFragment();
    }

    /**
     * @param permission 权限
     * @return 是否拥有某个权限
     */
    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求权限
     *
     * @param permissions 权限list
     */
    public void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSION_CODE);
    }

    /**
     * 是否勾选了不在提示
     *
     * @param permission 权限
     * @return true 勾选了不在提示， false没有勾选不在提示，只是拒绝了某个权限
     */
    private boolean isSelectedNoTips(@NonNull String permission) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
