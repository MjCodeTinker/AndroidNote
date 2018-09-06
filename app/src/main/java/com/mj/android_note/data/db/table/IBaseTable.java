package com.mj.android_note.data.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:04
 * Email       : miaojian_666@126.com
 * Description : 数据库表抽象接口
 */
public interface IBaseTable {
    /**
     * 提供创建表的语句
     *
     * @param db SQLiteDatabase
     * @return 创建表的字符串穿
     */
    String provideCreateTableSql(SQLiteDatabase db);

    /**
     * 数据库升级
     *
     * @param db         SQLiteDatabase
     * @param oldVersion 数据库旧版本号
     * @param newVersion 数据库新版本号
     */
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
