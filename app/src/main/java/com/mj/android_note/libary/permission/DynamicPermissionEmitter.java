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

public class DynamicPermissionEmitter {

    /**
     * 申请权限的回调
     */
    public interface ApplyPermissionsCallback {
        /**
         * 权限以map形式返回
         *
         * @param permissionEntityMap key value 形式 存储权限实体类
         */
        void result(Map<String, DynamicPermissionEntity> permissionEntityMap);
    }

    private static final String DYNAMIC_PERMISSION_FRAGMENT_TAG = "DynamicPermissionFragment";

    private DynamicPermissionFragment dynamicPermissionFragment;

    public DynamicPermissionEmitter(@NonNull FragmentActivity activity) {
        generateApplyPermissionFragment(activity.getSupportFragmentManager());
    }

    public DynamicPermissionEmitter(@NonNull Fragment fragment) {
        generateApplyPermissionFragment(fragment.getChildFragmentManager());
    }

    /**
     * 生成申请权限的fragment
     *
     * @param fragmentManager fragmentManager
     */
    private void generateApplyPermissionFragment(FragmentManager fragmentManager) {
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

}
