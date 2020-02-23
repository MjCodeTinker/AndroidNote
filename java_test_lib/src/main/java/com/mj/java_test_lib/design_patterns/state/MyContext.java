package com.mj.java_test_lib.design_patterns.state;

/**
 * Author      : MJ
 * Date        : 2020-02-23--17:23
 * Email       : miaojian_666@126.com
 * Description :
 */
public class MyContext {
    private IState iState;
    private String name;

    public MyContext(String name) {
        this.name = name;
    }

    public IState getState() {
        return iState;
    }

    public void setState(IState iState) {
        this.iState = iState;
    }

    public String getName() {
        return name;
    }
}
