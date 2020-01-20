package com.mj.android_note.base.serializable_parcelable;

import com.mj.lib.base.log.LogUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -8765029069536118067L;

    public String name;
    private int age;
    // 被transient修饰的变量，不会被序列化和反序列化
    public transient String password;
    // static 修饰的字段经过验证，也可以被序列化，和反序列化
    public static String email;

    public void setAge(int age) {
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        LogUtil.e("mj", "User writeObject is invoke...");
        out.defaultWriteObject();
        out.writeUTF(name);
        out.writeInt(age);
        out.writeUTF(password);
        out.writeUTF(email);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        LogUtil.e("mj", "User readObject is invoke...");
        try {
            in.defaultReadObject();
            name = in.readUTF();
            age = in.readInt();
            password = in.readUTF();
            email = in.readUTF();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
