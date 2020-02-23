package com.mj.java_test_lib.design_patterns.bridge;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:06
 * Email       : miaojian_666@126.com
 * Description :
 */
public class RectAngle extends Shape {

    private int x, y;

    public RectAngle(int x, int y, IDrawApi iDrawApi) {
        super(iDrawApi);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        String draw = iDrawApi.draw(0, x, y);
        System.out.println(draw + ",矩形");
    }
}
