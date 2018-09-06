package com.mj.android_note;

import com.mj.android_note.data.db.table.FolderTable;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:41
 * Email       : miaojian_666@126.com
 * Description : 数据库测试
 */
public class DbTest {
    @Test
    public void generateCreatingTableSql() {
        //1.创建表的字符串是否正确
        String sql = "CREATE TABLE folder(_id INTEGER PRIMARY KEY AUTOINCREMENT)";
        FolderTable folderTable = new FolderTable();
        assertEquals(sql, folderTable.provideCreateTableSql(null));
    }
}
