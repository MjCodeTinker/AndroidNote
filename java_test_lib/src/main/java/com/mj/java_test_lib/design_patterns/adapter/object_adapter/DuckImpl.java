package com.mj.java_test_lib.design_patterns.adapter.object_adapter;

/**
 * Author      : MJ
 * Date        : 2020-02-22--20:04
 * Email       : miaojian_666@126.com
 * Description :
 */
public class DuckImpl implements IDuck {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }

    @Override
    public void fly() {
        System.out.println("起飞了");
    }
}
