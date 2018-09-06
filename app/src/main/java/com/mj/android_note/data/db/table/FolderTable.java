package com.mj.android_note.data.db.table;

import android.database.sqlite.SQLiteDatabase;

import com.mj.android_note.app.AppConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:04
 * Email       : miaojian_666@126.com
 * Description : 文件夹表
 */
public class FolderTable extends AbstractTable {

    /**
     * 提供创建文件夹表的sql
     *
     * @param db SQLiteDatabase
     * @return sql字符串
     */
    @Override
    public String provideCreateTableSql(SQLiteDatabase db) {
        Map<String, String> columnMap = getIdIncrementMap();
        return generateCreatingTableSql(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, columnMap);
    }

    /**
     * 数据库升级
     *
     * @param db         SQLiteDatabase
     * @param oldVersion 数据库旧版本号
     * @param newVersion 数据库新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
