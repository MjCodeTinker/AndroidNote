package com.mj.java_test_lib.design_patterns.decorator.component;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:07
 * Email       : miaojian_666@126.com
 * Description : 饮料的抽象接口
 */
public interface Beverage {
    /**
     * 获取描述
     *
     * @return 饮料的描述
     */
    String description();

    /**
     * 花费的价格
     *
     * @return 花费的价格
     */
    float cost();
}
