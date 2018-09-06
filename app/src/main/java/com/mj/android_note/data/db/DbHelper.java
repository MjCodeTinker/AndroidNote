package com.mj.android_note.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mj.android_note.app.AppConfig;
import com.mj.android_note.data.db.table.FolderTable;
import com.mj.android_note.data.db.table.IBaseTable;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : SQLite数据库类
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, AppConfig.DbConfig.DB_NAME, null, AppConfig.DbConfig.DB_VERSION);
    }

    private IBaseTable[] iBaseTables = {new FolderTable()};

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, TAG + " onCreate");
        for (IBaseTable iBaseTable : iBaseTables) {
            db.execSQL(iBaseTable.provideCreateTableSql(db));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "db onUpgrade ... oldVersion = " + oldVersion + "--newVersion=" + newVersion);
        for (IBaseTable iBaseTable : iBaseTables) {
            iBaseTable.onUpgrade(db, oldVersion, newVersion);
        }
    }


}
