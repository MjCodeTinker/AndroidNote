package com.mj.java_test_lib.design_patterns.adapter.object_adapter;

/**
 * Author      : MJ
 * Date        : 2020-02-22--20:04
 * Email       : miaojian_666@126.com
 * Description :
 */
public class CockImpl implements ICock {


    @Override
    public void gobble() {
        System.out.println("咕咕叫");
    }

    @Override
    public void fly() {
        System.out.println("起飞了");
    }
}
