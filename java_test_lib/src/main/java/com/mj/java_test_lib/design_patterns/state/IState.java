package com.mj.java_test_lib.design_patterns.state;

/**
 * Author      : MJ
 * Date        : 2020-02-23--17:22
 * Email       : miaojian_666@126.com
 * Description :
 */
public interface IState {
    void doAction(MyContext myContext);
    String getStateDescription(MyContext myContext);
}
