package com.mj.android_note.libary.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/10/12--11:58
 * Email       : miaojian@conew.com
 * Description : 动态权限发射器
 */

public final class DynamicPermissionEmitter {

    private static final String TAG = "DynamicPermissionEmitter";

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
     * fragment manager
     */
    private FragmentManager fragmentManager;

    /**
     * 构造方法
     *
     * @param activity fragmentActivity
     */
    public DynamicPermissionEmitter(@NonNull FragmentActivity activity) {
        try {
            generateApplyPermissionFragment(activity.getSupportFragmentManager());

        } catch (Exception e) {
            handleFragmentException(activity, e);
        }
    }

    /**
     * 构造方法
     *
     * @param fragment v4 包下的 fragment
     */
    @SuppressLint("LongLogTag")
    public DynamicPermissionEmitter(@NonNull Fragment fragment) {
        try {
            generateApplyPermissionFragment(fragment.getChildFragmentManager());
        } catch (Exception e) {
            Log.e(TAG, "DynamicPermissionEmitter fragment", e);
        }
    }


    /**
     * 用户可能不知道当前是activity还是context 直接通过context转化成FragmentActivity
     *
     * @param activity fragment
     * @param e        exception
     */
    @SuppressLint("LongLogTag")
    private void handleFragmentException(@NonNull FragmentActivity activity, Exception e) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        if (supportFragmentManager != null) {
            List<Fragment> fragments = supportFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (TAG.equals(fragment.getTag())) {
                    continue;
                }
                try {
                    generateApplyPermissionFragment(fragment.getChildFragmentManager());
                } catch (Exception e1) {
                    Log.e(TAG, "DynamicPermissionEmitter activity", e);
                }
                break;
            }
        }
    }

    /**
     * 生成申请权限的fragment
     *
     * @param fragmentManager fragmentManager
     */
    private void generateApplyPermissionFragment(@NonNull FragmentManager fragmentManager) throws Exception {
        this.fragmentManager = fragmentManager;
        Fragment mFragment = fragmentManager.findFragmentByTag(DYNAMIC_PERMISSION_FRAGMENT_TAG);
        // 保证一个activity 或 fragment 只添加一个permission fragment
        if (mFragment != null) {
            dynamicPermissionFragment = (DynamicPermissionFragment) mFragment;
        } else {
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
    @SuppressLint("LongLogTag")
    public void emitterPermission(ApplyPermissionsCallback applyPermissionsCallback, String... permissions) {

        if (permissions == null || permissions.length == 0) {
            throw new NullPointerException("permissions no nulls allowed...");
        }
        try {
            if (dynamicPermissionFragment == null && fragmentManager != null) {
                generateApplyPermissionFragment(fragmentManager);
            }
            if (dynamicPermissionFragment != null) {
                dynamicPermissionFragment.checkRegisteredPermissionInManifest(permissions);
                dynamicPermissionFragment.setApplyPermissionsCallback(applyPermissionsCallback);
                dynamicPermissionFragment.commitPermission(permissions);
            }
        } catch (Exception e) {
            Log.e(TAG, "emitterPermission", e);
        }
    }

    /**
     * 考虑到部分地方仅仅需要检查权限,不需要申请权限，所以加了此方法
     *
     * @param permission permission
     * @return 是否有某个权限
     */
    public boolean checkSelfPermission(String permission) {
        return dynamicPermissionFragment != null && dynamicPermissionFragment.hasPermission(permission);
    }

    /**
     * 判断是否有某个权限
     *
     * @param activity   activity
     * @param permission 权限名称
     * @return 是否有某个存储权限
     */
    public static boolean hasPermission(Activity activity, String permission) {
        return activity != null && !activity.isFinishing() && PermissionChecker.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
