package com.mj.android_note.data.db.table.in;

import com.mj.android_note.bean.FileOrFolderBean;

import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/9/6--下午11:32
 * Email       : miaojian_666@126.com
 * Description : 文件夹表接口
 */
public interface IFolder {

    /**
     * 插入一条文件或文件夹
     *
     * @param bean 文件或文件夹bean
     * @return 处理结果码
     * @see ResultCode
     */
    int insertFileOrFolder(FileOrFolderBean bean);

    /**
     * 删除一条文件或文件夹
     *
     * @param bean 文件或文件夹bean
     * @return 处理结果码
     * @see ResultCode
     */
    int deleteFileOrFolder(FileOrFolderBean bean);

    /**
     * 更新一条文件或文件夹
     *
     * @param bean 文件或文件夹bean
     * @return 处理结果码
     * @see ResultCode
     */
    int updateFileOrFolder(FileOrFolderBean bean);

    /**
     * 查询子文件夹中的内容
     *
     * @param bean 文件或文件夹bean
     * @return 处理结果码
     * @see ResultCode
     */
    List<FileOrFolderBean> findChildFolderContent(FileOrFolderBean bean);

    /**
     * 文件或文件夹是否存在
     *
     * @param bean 文件或文件夹bean
     * @return 处理结果码
     * @see ResultCode
     */
    boolean isExistFileOrFolder(FileOrFolderBean bean);


    public interface ResultCode {
        public final int SUCCESS = 0;//成功
        public final int ERROR_UNKNOW = 1000;//位置错误
        public final int ERROR_FILE_EXIST = 1001;//文件已经存在
        public final int ERROR_FOLDER_EXIST = 1002;//文件夹已经存在
        public final int ERROR_FILE_NOT_EXIST = 1003;//文件不存在
        public final int ERROR_FOLDER_NOT_EXIST = 1004;//文件夹不存在
    }

}