package com.mj.android_note.libary.permission;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/10/12--11:58
 * Email       : miaojian@conew.com
 * Description : 动态权限发射器
 */

public final class DynamicPermissionEmitter {

    /**
     * 申请权限的回调
     */
    public interface ApplyPermissionsCallback {
        /**
         * 权限以map形式返回
         *
         * @param permissionEntityMap key value 形式 存储权限实体类,key 为权限名称
         */
        void applyPermissionResult(Map<String, DynamicPermissionEntity> permissionEntityMap);
    }

    /**
     * 动态权限申请的 fragment TAG
     */
    private static final String DYNAMIC_PERMISSION_FRAGMENT_TAG = "DynamicPermissionFragment";

    /**
     * 动态权限Fragment
     */
    private DynamicPermissionFragment dynamicPermissionFragment;

    /**
     * 构造方法
     *
     * @param activity fragmentActivity
     */
    public DynamicPermissionEmitter(@NonNull FragmentActivity activity) {
        generateApplyPermissionFragment(activity.getSupportFragmentManager());
    }

    /**
     * 构造方法
     *
     * @param fragment v4 包下的 fragment
     */
    public DynamicPermissionEmitter(@NonNull Fragment fragment) {
        generateApplyPermissionFragment(fragment.getChildFragmentManager());
    }

    /**
     * 生成申请权限的fragment
     *
     * @param fragmentManager fragmentManager
     */
    private void generateApplyPermissionFragment(@NonNull FragmentManager fragmentManager) {
        Fragment mFragment = fragmentManager.findFragmentByTag(DYNAMIC_PERMISSION_FRAGMENT_TAG);
        if (mFragment == null) {
            dynamicPermissionFragment = DynamicPermissionFragment.newInstance();
            fragmentManager
                    .beginTransaction()
                    .add(dynamicPermissionFragment, DYNAMIC_PERMISSION_FRAGMENT_TAG)
                    .commitNow();
        }
    }

    /**
     * 发射权限
     *
     * @param applyPermissionsCallback 回调
     * @param permissions              权限数组
     */
    public void emitterPermission(@NonNull ApplyPermissionsCallback applyPermissionsCallback, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            throw new NullPointerException("permissions no nulls allowed...");
        }
        dynamicPermissionFragment.checkRegisteredPermissionInManifest(permissions);
        dynamicPermissionFragment.setApplyPermissionsCallback(applyPermissionsCallback);
        dynamicPermissionFragment.commitPermission(permissions);
    }

    /**
     * 考虑到部分地方仅仅需要检查权限,不需要申请权限，所以加了此方法
     *
     * @param permission permission
     * @return 是否有某个权限
     */
    public boolean checkSelfPermission(String permission) {
        return dynamicPermissionFragment.hasPermission(permission);
    }

}