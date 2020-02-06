package com.mj.java_test_lib.design_patterns.decorator.component;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:09
 * Email       : miaojian_666@126.com
 * Description : 红茶
 */
public class RedTea implements Beverage {
    @Override
    public String description() {
        return "红茶";
    }

    @Override
    public float cost() {
        return 10;
    }
}
