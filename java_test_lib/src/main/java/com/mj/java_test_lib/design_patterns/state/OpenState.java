package com.mj.java_test_lib.design_patterns.state;

/**
 * Author      : MJ
 * Date        : 2020-02-23--17:24
 * Email       : miaojian_666@126.com
 * Description :
 */
public class OpenState implements IState {
    @Override
    public void doAction(MyContext myContext) {
        System.out.println("当前是打开状态");
        myContext.setState(this);
    }

    @Override
    public String getStateDescription(MyContext myContext) {
        return myContext.getName() + ",当前是打开状态";
    }
}
