package com.mj.android_note.data.db;

import com.mj.android_note.app.AppConfig;
import com.mj.android_note.app.NoteApplication;
import com.mj.android_note.data.db.table.in.IFolder;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 数据库管理类
 */
public class DbManager {

    private DbHelper dbHelper;

    public static DbManager getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static DbManager instance = new DbManager();
    }

    private DbManager() {
        dbHelper = new DbHelper(NoteApplication.getInstance());
    }

    /**
     * 获取文件夹表
     *
     * @return 文件夹表
     */
    public IFolder getFolderTable() {
        return (IFolder) dbHelper.getTableMap().get(AppConfig.DbConfig.DB_TABLE_NAME_FOLDER);
    }

}
