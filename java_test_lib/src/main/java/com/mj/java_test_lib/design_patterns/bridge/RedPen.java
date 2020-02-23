package com.mj.java_test_lib.design_patterns.bridge;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:01
 * Email       : miaojian_666@126.com
 * Description :
 */
public class RedPen implements IDrawApi {
    @Override
    public String draw(int radius, int x, int y) {
        return "用红色的笔绘制";
    }
}
