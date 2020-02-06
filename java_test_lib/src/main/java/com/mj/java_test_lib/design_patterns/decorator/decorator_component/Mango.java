package com.mj.java_test_lib.design_patterns.decorator.decorator_component;

import com.mj.java_test_lib.design_patterns.decorator.component.Beverage;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Condiment;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:13
 * Email       : miaojian_666@126.com
 * Description : 芒果调料
 */
public class Mango extends Condiment {

    private Beverage beverage;

    public Mango(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String description() {
        return beverage.description() + ", 加芒果";
    }

    @Override
    public float cost() {
        return beverage.cost() + 3;
    }

}
