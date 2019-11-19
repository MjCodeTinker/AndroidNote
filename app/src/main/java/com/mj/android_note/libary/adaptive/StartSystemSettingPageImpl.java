package com.mj.android_note.libary.adaptive;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import android.widget.Toast;

/**
 * Author      : MJ
 * Date        : 2018/11/9--17:19
 * Email       : miaojian@conew.com
 * Description :
 */

public class StartSystemSettingPageImpl implements IAppAdaptation<SystemSettingPageEntity> {

    private static final String MARK = Build.MANUFACTURER.toLowerCase();

     interface ISystemSettingPageCompat {
        Intent buildIntent(@NonNull Context context);
        void showGuide(Context context);
    }

    @Override
    public void beganToFit(SystemSettingPageEntity adaptationEntity) {
        SystemSettingPageCompatWrapper iSystemSettingPage;

        if (MARK.contains("huawei")) {

        } else if (MARK.contains("xiaomi")) {

        } else if (MARK.contains("oppo")) {

        } else if (MARK.contains("vivo")) {

        } else if (MARK.contains("meizu")) {

        } else {

        }

        iSystemSettingPage = new MUISystemSettingPageCompatImpl();
        Intent intent = iSystemSettingPage.buildIntent(adaptationEntity.getContext());
        adaptationEntity.getContext().startActivity(intent);
        iSystemSettingPage.showGuide(adaptationEntity.getContext());
    }


    private class SystemSettingPageCompatWrapper implements ISystemSettingPageCompat{

        @Override
        public Intent buildIntent(@NonNull Context context) {
            return null;
        }

        @Override
        public void showGuide(Context context) {
            Toast.makeText(context, "我的天呐", Toast.LENGTH_SHORT).show();
        }

    }

    private class MUISystemSettingPageCompatImpl extends SystemSettingPageCompatWrapper {

        @Override
        public Intent buildIntent(@NonNull Context context) {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.putExtra("extra_pkgname", context.getPackageName());
            if (hasActivity(context, intent)) return intent;
            intent.setPackage("com.miui.securitycenter");
            if (hasActivity(context, intent)) return intent;
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            return intent;
        }
    }


    private boolean hasActivity(@NonNull Context context, @NonNull Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

}
