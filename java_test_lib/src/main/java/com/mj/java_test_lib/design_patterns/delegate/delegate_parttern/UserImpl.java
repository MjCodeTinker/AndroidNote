package com.mj.java_test_lib.design_patterns.delegate.delegate_parttern;

/**
 * Author      : MJ
 * Date        : 2020-02-21--15:08
 * Email       : miaojian_666@126.com
 * Description :
 */
public class UserImpl implements IUser {
    @Override
    public String getUserName() {
        return "我是User的实现类";
    }
}
