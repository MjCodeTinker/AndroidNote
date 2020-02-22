package com.mj.java_test_lib.design_patterns.adapter.object_adapter;

/**
 * Author      : MJ
 * Date        : 2020-02-22--20:07
 * Email       : miaojian_666@126.com
 * Description :
 */
public class DuckAdapter implements IDuck {
    private ICock iCock;

    public DuckAdapter(ICock iCock) {
        this.iCock = iCock;
    }

    @Override
    public void quack() {
        iCock.gobble();
    }

    @Override
    public void fly() {
        iCock.fly();
    }
}
