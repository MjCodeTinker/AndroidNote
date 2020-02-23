package com.mj.java_test_lib.design_patterns.strategy;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:06
 * Email       : miaojian_666@126.com
 * Description :
 */
public class RedPaintStrategy implements StrategyPaint{
    @Override
    public void draw() {
        System.out.println("用红笔画");
    }
}
