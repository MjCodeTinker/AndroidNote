package com.mj.android_note.bean;

import com.mj.android_note.data.db.table.DbFolderImpl;

/**
 * Author      : MJ
 * Date        : 2018/9/8--下午7:45
 * Email       : miaojian_666@126.com
 * Description : 文件或文件夹实体类
 */
public class FileOrFolderBean {
    private int id;//每条数据的id
    private boolean isFolder;//是否为文件夹
    private String fileName;//文件名称
    private String folderName = DbFolderImpl.DEFAULT_FOLDER_NAME;//文件夹名称
    private String modifyFolderName;//将要修改的文件夹名称
    private String modifyFileName;//将要修改的文件名称
    private long createTime = Long.parseLong(DbFolderImpl.DEFAULT_CREATE_TIME);//创建时间
    private int position = Integer.parseInt(DbFolderImpl.DEFAULT_POSITION);//本条数据的位置
    private int parentFolderID = Integer.parseInt(DbFolderImpl.DEFAULT_PARENT_FOLDER_ID);//父文件夹id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getModifyFolderName() {
        return modifyFolderName;
    }

    public void setModifyFolderName(String modifyFolderName) {
        this.modifyFolderName = modifyFolderName;
    }

    public String getModifyFileName() {
        return modifyFileName;
    }

    public void setModifyFileName(String modifyFileName) {
        this.modifyFileName = modifyFileName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getParentFolderID() {
        return parentFolderID;
    }

    public void setParentFolderID(int parentFolderID) {
        this.parentFolderID = parentFolderID;
    }


    @Override
    public String toString() {
        return "FileOrFolderBean{" +
                "id=" + id +
                ", isFolder=" + isFolder +
                ", fileName='" + fileName + '\'' +
                ", folderName='" + folderName + '\'' +
                ", modifyFolderName='" + modifyFolderName + '\'' +
                ", createTime=" + createTime +
                ", position=" + position +
                ", parentFolderID=" + parentFolderID +
                '}';
    }
}
