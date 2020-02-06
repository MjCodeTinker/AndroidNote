package com.mj.java_test_lib.design_patterns.decorator.component;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:15
 * Email       : miaojian_666@126.com
 * Description : 绿茶
 */
public class GreenTea implements Beverage {
    @Override
    public String description() {
        return "绿茶";
    }

    @Override
    public float cost() {
        return 9;
    }
}
