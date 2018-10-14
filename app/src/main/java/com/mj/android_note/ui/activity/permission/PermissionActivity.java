package com.mj.android_note.ui.activity.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mj.android_note.R;
import com.mj.android_note.libary.permission.DynamicPermissionEmitter;
import com.mj.android_note.libary.permission.DynamicPermissionEntity;
import com.mj.android_note.ui.activity.BaseActivity;
import com.mj.android_note.utils.LogUtil;
import com.mj.android_note.utils.ToastUtils;

import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/10/11--15:34
 * Email       : miaojian@conew.com
 * Description : 6.0 动态权限
 */

public class PermissionActivity extends BaseActivity {

    private static final String TAG = "PermissionActivity";
    DynamicPermissionEmitter dynamicPermissionEmitter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        dynamicPermissionEmitter = new DynamicPermissionEmitter(this);
        findViewById(R.id.permission_btn_open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicPermissionEmitter.emitterPermission(new DynamicPermissionEmitter.ApplyPermissionsCallback() {
                    @Override
                    public void result(Map<String, DynamicPermissionEntity> permissionEntityMap) {
                        LogUtil.e(TAG, "permissionEntityMap.size=" + permissionEntityMap.size());
                        DynamicPermissionEntity dynamicPermissionEntity = permissionEntityMap.get(Manifest.permission.CAMERA);
                        switch (dynamicPermissionEntity.getPermissionState()) {
                            case DynamicPermissionEntity.PERMISSION_DENIED:
                                ToastUtils.showShortToast("很单纯的拒绝了权限");
                                break;
                            case DynamicPermissionEntity.PERMISSION_GRANTED:
                                ToastUtils.showShortToast("获取了权限，开始搞事情吧");
                                break;
                            case DynamicPermissionEntity.PERMISSION_DENIED_AND_SELECTED_NO_PROMPT:
                                ToastUtils.showShortToast("已经拒绝了权限 并且不再提示");
                                break;
                            case DynamicPermissionEntity.PERMISSION_UN_HANDLE:
                                ToastUtils.showShortToast("6.0以下国内厂商手机无法检查到是否拒绝了权限.请通过 try catch 处理");
                                break;
                            default:
                                break;
                        }
                    }
                }, Manifest.permission.SEND_SMS, Manifest.permission.CAMERA);
            }
        });
    }

}
