package com.mj.android_note.data.db.table;

import android.text.TextUtils;

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

    // id列
    static final String COLUMN_ID = "_id";
    // id自增长条件
    static final String COLUMN_ID_CONDITIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";

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
     * 获取id自增长的map
     *
     * @return 存放id自增长的map
     */
    Map<String, String> getIdIncrementMap() {
        Map<String, String> map = new HashMap<>();
        map.put(COLUMN_ID, COLUMN_ID_CONDITIONS);
        return map;
    }
}
