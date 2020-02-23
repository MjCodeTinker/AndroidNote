package com.mj.java_test_lib.design_patterns.bridge;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:04
 * Email       : miaojian_666@126.com
 * Description :
 */
public class Circle extends Shape {

    private int radius;

    public Circle(int radius, IDrawApi iDrawApi) {
        super(iDrawApi);
        this.radius = radius;
    }

    @Override
    public void draw() {
        String draw = iDrawApi.draw(radius, 0, 0);
        System.out.println(draw + ",圆形");
    }
}
