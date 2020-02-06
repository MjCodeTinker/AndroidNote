package com.mj.java_test_lib.design_patterns.decorator.decorator_component;

import com.mj.java_test_lib.design_patterns.decorator.component.Beverage;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:17
 * Email       : miaojian_666@126.com
 * Description : 西瓜
 */
public class Watermelon extends Condiment {

    private Beverage beverage;

    public Watermelon(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String description() {
        return beverage.description() + ", 加西瓜";
    }

    @Override
    public float cost() {
        return beverage.cost() + 5;
    }
}
