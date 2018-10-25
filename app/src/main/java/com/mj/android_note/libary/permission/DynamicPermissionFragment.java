package com.mj.android_note.libary.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/10/11--19:28
 * Email       : miaojian@conew.com
 * Description : 申请权限的fragment
 */

public final class DynamicPermissionFragment extends Fragment {

    // 权限的请求码
    private static final int REQUEST_PERMISSION_CODE = 1000;
    private static final String TAG = "CandyPermissionFragment";

    // activity
    private Activity activity;
    // 申请权限的回调
    private DynamicPermissionEmitter.ApplyPermissionsCallback applyPermissionsCallback;
    // 存放动态权限实体类与权限名称的映射关系
    private Map<String, DynamicPermissionEntity> permissionEntityMap;

    public void setApplyPermissionsCallback(DynamicPermissionEmitter.ApplyPermissionsCallback applyPermissionsCallback) {
        this.applyPermissionsCallback = applyPermissionsCallback;
    }

    public static DynamicPermissionFragment newInstance() {
        return new DynamicPermissionFragment();
    }

    /**
     * 提交要申请的权限
     * 此方法会提取出未授权的权限与已授权的权限，支队未授权的权限进行申请
     *
     * @param permissions 权限数组
     */
    public void commitPermission(String... permissions) {
        permissionEntityMap = new HashMap<>();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //6.0 以下 无法检测到权限
            for (String permission : permissions) {
                DynamicPermissionEntity dynamicPermissionEntity = new DynamicPermissionEntity();
                dynamicPermissionEntity.setPermissionName(permission);
                dynamicPermissionEntity.setPermissionState(DynamicPermissionEntity.PERMISSION_UN_HANDLE);
                permissionEntityMap.put(permission, dynamicPermissionEntity);
            }
            applyPermissionsCallback.applyPermissionResult(permissionEntityMap);
            return;
        }

        List<String> unGrantedPermissions = new ArrayList<>(); //未授权的权限列表
        for (String permission : permissions) {
            if (hasPermission(permission)) {
                DynamicPermissionEntity dynamicPermissionEntity = new DynamicPermissionEntity();
                dynamicPermissionEntity.setPermissionName(permission);
                dynamicPermissionEntity.setPermissionState(DynamicPermissionEntity.PERMISSION_GRANTED);
                permissionEntityMap.put(permission, dynamicPermissionEntity);
            } else {
                unGrantedPermissions.add(permission);
            }
        }

        if (unGrantedPermissions.size() == 0) {
            applyPermissionsCallback.applyPermissionResult(permissionEntityMap);
            return;
        }
        String[] array = new String[unGrantedPermissions.size()];
        for (int i = 0; i < unGrantedPermissions.size(); i++) {
            array[i] = unGrantedPermissions.get(i);
        }
        // 开始请求权限
        applyDynamicPermissions(array);
    }

    /**
     * @param permission 权限
     * @return 是否拥有某个权限
     */
    public boolean hasPermission(String permission) {
        //ContextCompat这种方式检查权限在小米手机上会有问题，使用PermissionChecker 检查权限会正常
        //return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
        return PermissionChecker.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求权限
     *
     * @param permissions 权限list
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void applyDynamicPermissions(String... permissions) {
        requestPermissions(permissions, REQUEST_PERMISSION_CODE);
    }

    /**
     * 检查要请求的权限在manifest中是否注册
     *
     * @param permissions 权限列表
     */
    public void checkRegisteredPermissionInManifest(String... permissions) {
        PackageManager pm = activity.getPackageManager();
        if (pm != null) {
            try {
                String[] requestedPermissions = pm.getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
                if (requestedPermissions != null) {
                    if (!Arrays.asList(requestedPermissions).containsAll(Arrays.asList(permissions))) {
                        throw new IllegalArgumentException("please register the permissions in manifest...");
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "haveRegisteredPermissionInManifest", e);
            }
        }
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
        if (REQUEST_PERMISSION_CODE == requestCode && permissions.length == grantResults.length) {

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grant = grantResults[i];
                DynamicPermissionEntity dynamicPermissionEntity = new DynamicPermissionEntity();
                dynamicPermissionEntity.setPermissionName(permission);
                if (grant == PackageManager.PERMISSION_GRANTED) {
                    dynamicPermissionEntity.setPermissionState(DynamicPermissionEntity.PERMISSION_GRANTED);
                } else {
                    if (isSelectedNoTips(permission)) {
                        dynamicPermissionEntity.setPermissionState(DynamicPermissionEntity.PERMISSION_DENIED_AND_SELECTED_NO_PROMPT);
                    } else {
                        dynamicPermissionEntity.setPermissionState(DynamicPermissionEntity.PERMISSION_DENIED);
                    }
                }
                permissionEntityMap.put(permission, dynamicPermissionEntity);
            }
            applyPermissionsCallback.applyPermissionResult(permissionEntityMap);
        }
    }

}
