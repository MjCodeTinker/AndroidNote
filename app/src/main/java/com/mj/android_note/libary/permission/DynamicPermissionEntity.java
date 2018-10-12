package com.mj.android_note.libary.permission;

/**
 * Author      : MJ
 * Date        : 2018/10/11--19:16
 * Email       : miaojian@conew.com
 * Description : 权限的实体类
 */

public class DynamicPermissionEntity {


    //拒绝了此权限
    public static final int PERMISSION_DENIED = 1;
    //授予了权限
    public static final int PERMISSION_GRANTED = 2;
    //拒绝了此权限并且勾选了不在提示
    public static final int PERMISSION_DENIED_AND_SELECTED_NO_PROMPT = 3;
    //不能处理的权限 例如android 6.0 以下手机，请用try catch 捕获无权限的异常
    public static final int PERMISSION_UN_HANDLE = 4;

    // 权限名称
    private String permissionName;
    // 权限状态,默认为被拒绝的权限
    private int permissionState = PERMISSION_DENIED;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionState() {
        return permissionState;
    }

    public void setPermissionState(int permissionState) {
        this.permissionState = permissionState;
    }

    @Override
    public String toString() {
        return "DynamicPermissionEntity{" +
                "permissionName='" + permissionName + '\'' +
                ", permissionState=" + permissionState +
                '}';
    }
}
