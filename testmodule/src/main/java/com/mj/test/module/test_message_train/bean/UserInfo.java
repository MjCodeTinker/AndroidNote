package com.mj.test.module.test_message_train.bean;

/**
 * Author      : MJ
 * Date        : 2019/3/7--13:55
 * Email       : miaojian@conew.com
 * Description :
 */

public class UserInfo {

    public UserInfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public String age;

    @Override
    public String toString() {
        return "name: " + name + " -- age: " + age;
    }
}
