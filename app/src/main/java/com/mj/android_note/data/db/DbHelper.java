package com.mj.android_note.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mj.android_note.app.AppConfig;
import com.mj.android_note.data.db.table.DbFolderImpl;
import com.mj.android_note.data.db.table.IBaseTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : SQLite数据库类
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";
    private final Map<String, IBaseTable> tableMap = new HashMap<>();

    DbHelper(Context context) {
        super(context, AppConfig.DbConfig.DB_NAME, null, AppConfig.DbConfig.DB_VERSION);
        initTables();
    }

    //所有表都需要配置到map中 表明mapping表对象
    private void initTables() {
        tableMap.put(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, new DbFolderImpl(this));
    }

    //获取表map
    public Map<String, IBaseTable> getTableMap() {
        return tableMap;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, TAG + " onCreate");
        for (Map.Entry<String, IBaseTable> table : tableMap.entrySet()) {
            db.execSQL(table.getValue().provideCreateTableSql(db));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "db onUpgrade ... oldVersion = " + oldVersion + "--newVersion=" + newVersion);
        for (Map.Entry<String, IBaseTable> table : tableMap.entrySet()) {
            table.getValue().onUpgrade(db, oldVersion, newVersion);
        }
    }


}
