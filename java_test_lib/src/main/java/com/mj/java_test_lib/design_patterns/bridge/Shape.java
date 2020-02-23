package com.mj.java_test_lib.design_patterns.bridge;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:02
 * Email       : miaojian_666@126.com
 * Description :
 */
public abstract class Shape {
    protected IDrawApi iDrawApi;

    public Shape(IDrawApi iDrawApi) {
        this.iDrawApi = iDrawApi;
    }

    public abstract void draw();

}
