package com.mj.java_test_lib.design_patterns.delegate.delegate_parttern;

/**
 * Author      : MJ
 * Date        : 2020-02-21--15:09
 * Email       : miaojian_666@126.com
 * Description :
 */
public class UserProxy implements IUser {

    // 被代理的对象
    private IUser iUser;

    public UserProxy(IUser iUser) {
        this.iUser = iUser;
    }

    @Override
    public String getUserName() {
        return "我是代理包装类，给UserName 增加点缀，" + iUser.getUserName();
    }
}
