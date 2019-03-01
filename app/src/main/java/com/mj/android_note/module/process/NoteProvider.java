package com.mj.android_note.module.process;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Author      : MJ
 * Date        : 2018/12/25--15:49
 * Email       : miaojian@conew.com
 * Description :
 */

public class NoteProvider extends ContentProvider {
    private static final String TAG = "NoteProvider";
    public static int TEST_CONSTANT_FROM_MULTI_PROCESS_DEFAULT = 100;

    @Override
    public boolean onCreate() {
        Log.e(TAG, TAG + " onCreate() processName=" + getProcessName());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    String getProcessName() {
        String processName = "default";

        File cmdFile = new File("/proc/" + android.os.Process.myPid() + "/cmdline");

        if (cmdFile.exists() && !cmdFile.isDirectory()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdFile)));
                String procName = reader.readLine();

//                if (!TextUtils.isEmpty(procName) && getContext() != null) {
//                    processName = procName.trim().replace(getContext().getPackageName(), "").replace(":", "");
//                }
                return procName.trim();
            } catch (Exception ignored) {
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return processName;
    }

}
