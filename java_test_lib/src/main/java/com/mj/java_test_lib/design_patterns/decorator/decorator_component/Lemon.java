package com.mj.java_test_lib.design_patterns.decorator.decorator_component;

import com.mj.java_test_lib.design_patterns.decorator.component.Beverage;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:19
 * Email       : miaojian_666@126.com
 * Description : 柠檬调料
 */
public class Lemon extends Condiment {
    private Beverage beverage;

    public Lemon(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String description() {
        return beverage.description() + ",加柠檬";
    }

    @Override
    public float cost() {
        return beverage.cost() + 2;
    }
}
