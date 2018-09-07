package com.mj.android_note.data.db.table;

import android.database.sqlite.SQLiteDatabase;

import com.mj.android_note.R;
import com.mj.android_note.app.AppConfig;
import com.mj.android_note.data.db.DbHelper;
import com.mj.android_note.data.db.table.in.IFolderTable;
import com.mj.android_note.utils.LocalResourceUtil;

import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:04
 * Email       : miaojian_666@126.com
 * Description : 文件夹表
 */
public final class FolderTable extends AbstractTable implements IFolderTable {
    /**
     * 列名
     */
    private static final String COLUMN_FILE_NAME = "fileName";//文件名称
    private static final String COLUMN_IS_FOLDER = "isFolder";//是否是文件夹
    private static final String COLUMN_FOLDER_NAME = "folderName";//文件夹名称
    private static final String COLUMN_PARENT_FOLDER_ID = "parentFolderID";//父文件夹Id

    //默认文件夹名称
    private static final String DEFAULT_NOT_FOLDER = "0";//默认不是文件夹
    private static final String DEFAULT_FOLDER_NAME = LocalResourceUtil.getStr(R.string.default_folder_name);//默认文件夹默认名称
    private static final String DEFAULT_PARENT_FOLDER_ID = "-1";//默认父文件夹id ,如果为 -1 父文件夹为根目录

    //数据库帮助类
    private DbHelper dbHelper;

    public FolderTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * 提供创建文件夹表的sql
     *
     * @param db SQLiteDatabase
     * @return sql字符串
     */
    @Override
    public String provideCreateTableSql(SQLiteDatabase db) {
        Map<String, String> columnMap = getIdIncrementMap();
        columnMap.put(COLUMN_FILE_NAME, CONDITIONS_TEXT);
        columnMap.put(COLUMN_IS_FOLDER, CONDITIONS_INTEGER_DEFAULT + DEFAULT_NOT_FOLDER); //0为默认不是文件夹
        columnMap.put(COLUMN_FOLDER_NAME, CONDITIONS_TEXT_DEFAULT + DEFAULT_FOLDER_NAME);
        columnMap.put(COLUMN_PARENT_FOLDER_ID, CONDITIONS_INTEGER_DEFAULT + DEFAULT_PARENT_FOLDER_ID);
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
