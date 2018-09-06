package com.mj.android_note.data.db;

/**
 * Author      : MJ
 * Date        : 2018/9/4--下午10:02
 * Email       : miaojian_666@126.com
 * Description : 数据库管理类
 */
public class DbManager {

    public static DbManager getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static DbManager instance = new DbManager();
    }



}
