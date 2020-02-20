package com.mj.java_test_lib.design_patterns.factory.simple;

import java.security.PublicKey;

/**
 * Author      : MJ
 * Date        : 2020-02-20--17:29
 * Email       : miaojian_666@126.com
 * Description : 简单工厂模式（静态工厂）
 */
public class StaticFactory {

    public static A createA(int type) {
        return type == 0 ? new B() : new C();
    }

    public static abstract class A {
        public abstract String getName();
    }

    public static class B extends A {
        @Override
        public String getName() {
            return "i am A";
        }
    }

    public static class C extends A {

        @Override
        public String getName() {
            return "i am B";
        }
    }

}
