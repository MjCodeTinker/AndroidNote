package com.mj.java_test_lib.design_patterns.facade;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:27
 * Email       : miaojian_666@126.com
 * Description :
 */
public class RectAngleShape implements IFacadeShape {
    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}
