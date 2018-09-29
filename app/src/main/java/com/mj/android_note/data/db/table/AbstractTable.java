package com.mj.android_note.data.db.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.mj.android_note.data.db.DbHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 数据库表的抽象类，之后所有创建的表都继承与这个抽象类
 */
abstract class AbstractTable implements IBaseTable {
    // 类名
    private final String TAG = this.getClass().getSimpleName();

    static final String WHERE_CONDITIONS_AND = " AND ";
    static final String WHERE_CONDITIONS_OR = " OR ";


    // id列
    static final String COLUMN_ID = "_id";
    /**
     * 列条件
     */
    // id自增长条件
    private static final String CONDITIONS_ID_INCREMENT = "INTEGER PRIMARY KEY AUTOINCREMENT";
    static final String CONDITIONS_TEXT = "TEXT";//文本
    static final String CONDITIONS_INTEGER = "INTEGER";//整数
    static final String CONDITIONS_REAL = "REAL";//浮点数
    static final String CONDITIONS_BLOB = "BLOB";//大对象
    static final String CONDITIONS_TEXT_DEFAULT = "TEXT DEFAULT ";//带默认值的文本
    static final String CONDITIONS_INTEGER_DEFAULT = "INTEGER ";//带默认值的整数
    static final String CONDITIONS_REAL_DEFAULT = "REAL DEFAULT ";//带默认值的浮点数
    static final String CONDITIONS_BLOB_DEFAULT = "BLOB DEFAULT ";//带默认值的大对象

    private DbHelper dbHelper;

    public AbstractTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    SQLiteDatabase getWriteDb() {
        return dbHelper.getWritableDatabase();
    }

    SQLiteDatabase getReadDb() {
        return dbHelper.getReadableDatabase();
    }

    /**
     * 生成创建标的sql语句
     *
     * @param tableName 表名
     * @param columnMap key value 对应数据库字段与字段的限制
     * @return 创建标的sql字符串
     */
    String generateCreatingTableSql(String tableName, Map<String, String> columnMap) {
        if (null == columnMap) {
            throw new NullPointerException(TAG + " columnMap not null allowed...");
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("CREATE TABLE ");
        strBuilder.append(tableName);
        strBuilder.append("(");
        Set<Map.Entry<String, String>> entries = columnMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (true) {
            if (!(iterator.hasNext())) break;
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                throw new NullPointerException("key or value not nulls allowed...");
            }
            strBuilder.append(key).append(" ");
            strBuilder.append(value);
            if (iterator.hasNext()) {
                strBuilder.append(",");
            }
        }
        strBuilder.append(")");
        return strBuilder.toString();
    }

    /**
     * 生成where 条件字符串
     *
     * @param whereMap  条件key value  map
     * @param whereType 条件类型 and or
     * @return 条件语句
     */
    String generateWhereStr(Map<String, String> whereMap, String whereType) {
        if (null == whereMap) {
            throw new NullPointerException(TAG + " columnMap not null allowed...");
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entries = whereMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                throw new NullPointerException("key or value not nulls allowed...");
            }
            sb.append(key);
            sb.append(" = ");
            sb.append(value);
            if (TextUtils.isEmpty(whereType)) {
                break;
            }
            if (iterator.hasNext()) {
                sb.append(whereType);
            }
        }
        return sb.toString();
    }

    /**
     * 获取id自增长的map
     *
     * @return 存放id自增长的map
     */
    Map<String, String> getIdIncrementMap() {
        Map<String, String> map = new HashMap<>();
        map.put(COLUMN_ID, CONDITIONS_ID_INCREMENT);
        return map;
    }


    /**
     * 关闭cursor
     *
     * @param cursor 游标
     */
    void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
