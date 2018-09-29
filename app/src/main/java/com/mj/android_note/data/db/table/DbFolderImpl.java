package com.mj.android_note.data.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.mj.android_note.R;
import com.mj.android_note.app.AppConfig;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.data.db.DbHelper;
import com.mj.android_note.data.db.table.in.IFolder;
import com.mj.android_note.utils.LocalResourceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:04
 * Email       : miaojian_666@126.com
 * Description : 文件夹表
 */
public final class DbFolderImpl extends AbstractTable implements IFolder {
    /**
     * 列名
     */
    private static final String COLUMN_FILE_NAME = "fileName";//文件名称
    private static final String COLUMN_IS_FOLDER = "isFolder";//是否是文件夹
    private static final String COLUMN_FOLDER_NAME = "folderName";//文件夹名称
    private static final String COLUMN_PARENT_FOLDER_ID = "parentFolderID";//父文件夹Id
    private static final String COLUMN_POSITION = "position";//位置，方便排序使用
    private static final String COLUMN_CREATE_TIME = "createTime";//创建时间

    //默认文件夹名称
    private static final String DEFAULT_NOT_FOLDER = "0";//默认不是文件夹
    public static final String DEFAULT_FOLDER_NAME = LocalResourceUtil.getStr(R.string.default_folder_name);//默认文件夹默认名称
    public static final String DEFAULT_PARENT_FOLDER_ID = "-1";//默认父文件夹id ,如果为-1 父文件夹为根目录
    public static final String DEFAULT_POSITION = "0";//position默认值为0
    public static final String DEFAULT_CREATE_TIME = "0";//创建时间默认为0

    public DbFolderImpl(DbHelper dbHelper) {
        super(dbHelper);
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
        columnMap.put(COLUMN_IS_FOLDER, CONDITIONS_INTEGER_DEFAULT + DEFAULT_NOT_FOLDER);
        columnMap.put(COLUMN_FOLDER_NAME, CONDITIONS_TEXT_DEFAULT + DEFAULT_FOLDER_NAME);
        columnMap.put(COLUMN_PARENT_FOLDER_ID, CONDITIONS_INTEGER_DEFAULT + DEFAULT_PARENT_FOLDER_ID);
        columnMap.put(COLUMN_POSITION, CONDITIONS_INTEGER_DEFAULT + DEFAULT_POSITION);
        columnMap.put(COLUMN_CREATE_TIME, CONDITIONS_INTEGER_DEFAULT + DEFAULT_CREATE_TIME);
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

    @Override
    public int insertFileOrFolder(FileOrFolderBean bean) {

        if (bean.isFolder()) {
            if (TextUtils.isEmpty(bean.getFolderName())) {
                return ResultCode.ERROR_FOLDER_NMAE_IS_NULL;
            }
        } else {
            if (TextUtils.isEmpty(bean.getFileName())) {
                return ResultCode.ERROR_FILE_NAME_IS_NULL;
            }
        }

        if (isExistFileOrFolder(bean)) {
            return bean.isFolder() ? ResultCode.ERROR_FOLDER_EXIST : ResultCode.ERROR_FILE_EXIST;
        }

        ContentValues contentValues = generateContentValues(bean);

        long insert = getWriteDb().insert(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, null, contentValues);
        return insert >= 0 ? ResultCode.SUCCESS : ResultCode.ERROR_UN_KNOW;
    }

    @Override
    public int deleteFileOrFolder(FileOrFolderBean bean) {

        if (bean.isFolder()) {
            if (TextUtils.isEmpty(bean.getFolderName())) {
                return ResultCode.ERROR_FOLDER_NMAE_IS_NULL;
            }
        } else {
            if (TextUtils.isEmpty(bean.getFileName())) {
                return ResultCode.ERROR_FILE_NAME_IS_NULL;
            }
        }

        if (!isExistFileOrFolder(bean)) {
            return bean.isFolder() ? ResultCode.ERROR_FOLDER_NOT_EXIST : ResultCode.ERROR_FILE_NOT_EXIST;
        }

        String where;
        Map<String, String> map = new HashMap<>();
        map.put(COLUMN_PARENT_FOLDER_ID, String.valueOf(bean.getParentFolderID()));
        if (bean.isFolder()) {
            map.put(COLUMN_ID, String.valueOf(bean.getId()));
            where = generateWhereStr(map, WHERE_CONDITIONS_OR);
        } else {
            map.put(COLUMN_FILE_NAME, bean.getFileName());
            where = generateWhereStr(map, WHERE_CONDITIONS_AND);
        }
        int delete = getReadDb().delete(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, where, null);
        return delete >= 0 ? ResultCode.SUCCESS : ResultCode.ERROR_UN_KNOW;
    }

    @Override
    public int updateFileOrFolder(FileOrFolderBean bean) {

        if (bean.isFolder()) {
            if (TextUtils.isEmpty(bean.getFolderName())) {
                return ResultCode.ERROR_FOLDER_NMAE_IS_NULL;
            }
        } else {
            if (TextUtils.isEmpty(bean.getFileName())) {
                return ResultCode.ERROR_FILE_NAME_IS_NULL;
            }
        }

        if (!isExistFileOrFolder(bean)) {
            return bean.isFolder() ? ResultCode.ERROR_FOLDER_NOT_EXIST : ResultCode.ERROR_FILE_NOT_EXIST;
        }

        ContentValues contentValues = new ContentValues();
        String where;
        Map<String, String> map = new HashMap<>();
        map.put(COLUMN_PARENT_FOLDER_ID, String.valueOf(bean.getParentFolderID()));
        if (bean.isFolder()) {
            map.put(COLUMN_ID, String.valueOf(bean.getId()));
            where = generateWhereStr(map, WHERE_CONDITIONS_OR);
        } else {
            map.put(COLUMN_FILE_NAME, bean.getFileName());
            where = generateWhereStr(map, WHERE_CONDITIONS_AND);
        }

        int update = getWriteDb().update(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, contentValues, where, null);
        return update >= 0 ? ResultCode.SUCCESS : ResultCode.ERROR_UN_KNOW;
    }

    @Override
    public List<FileOrFolderBean> findChildFolderContent(FileOrFolderBean bean) {
        return null;
    }

    @Override
    public boolean isExistFileOrFolder(FileOrFolderBean bean) {
        String[] columns;
        String where;
        Map<String, String> map = new HashMap<>();
        map.put(COLUMN_PARENT_FOLDER_ID, String.valueOf(bean.getParentFolderID()));

        if (bean.isFolder()) {
            columns = new String[]{COLUMN_PARENT_FOLDER_ID, COLUMN_FOLDER_NAME};
            map.put(COLUMN_FOLDER_NAME, bean.getFolderName());
            where = generateWhereStr(map, WHERE_CONDITIONS_AND);
        } else {
            columns = new String[]{COLUMN_PARENT_FOLDER_ID, COLUMN_FILE_NAME};
            map.put(COLUMN_FILE_NAME, bean.getFileName());
            where = generateWhereStr(map, WHERE_CONDITIONS_AND);
        }

        Cursor query = getReadDb().query(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER, columns, where, null, null, null, null);
        boolean result = query != null && query.getCount() > 0;
        closeCursor(query);
        return result;
    }

    /**
     * 生成contentValues
     *
     * @param bean bean
     * @return ContentValues
     */
    private ContentValues generateContentValues(FileOrFolderBean bean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FILE_NAME, bean.getFileName());
        contentValues.put(COLUMN_FOLDER_NAME, bean.getFolderName());
        contentValues.put(COLUMN_IS_FOLDER, bean.isFolder());
        contentValues.put(COLUMN_PARENT_FOLDER_ID, bean.getParentFolderID());
        contentValues.put(COLUMN_POSITION, bean.getPosition());
        contentValues.put(COLUMN_CREATE_TIME, bean.getCreateTime());
        return contentValues;
    }

}
