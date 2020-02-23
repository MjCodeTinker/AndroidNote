package com.mj.java_test_lib.design_patterns.bridge;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:02
 * Email       : miaojian_666@126.com
 * Description :
 */
public class GreenPen implements IDrawApi {
    @Override
    public String draw(int radius, int x, int y) {
        return "用绿色的笔绘制";
    }
}
