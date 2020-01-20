package com.mj.android_note.base.serializable_parcelable;

import com.mj.lib.base.physical_storage.AppPathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializableAndParcelable {


    // 序列化User对象
    public void serializableUser(User user) {
        ObjectOutputStream outputStream = null;
        try {
            File file = new File(AppPathUtils.getAppPrivateCachesPath() + "testSerial.so");
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(user);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 反序列化User对象
    public User deSerializableUser() {
        File file = new File(AppPathUtils.getAppPrivateCachesPath() + "testSerial.so");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return (User) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
