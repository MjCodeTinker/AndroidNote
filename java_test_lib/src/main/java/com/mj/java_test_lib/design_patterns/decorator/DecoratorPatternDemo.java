package com.mj.java_test_lib.design_patterns.decorator;

import com.mj.java_test_lib.design_patterns.decorator.component.Beverage;
import com.mj.java_test_lib.design_patterns.decorator.component.GreenTea;
import com.mj.java_test_lib.design_patterns.decorator.component.RedTea;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Lemon;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Mango;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Watermelon;

/**
 * Author      : MJ
 * Date        : 2020-02-06--20:20
 * Email       : miaojian_666@126.com
 * Description : 装饰者模式测试类
 */
public class DecoratorPatternDemo {

    public static void main(String[] args) {
        // 先来一杯红茶
        Beverage beverage = new RedTea();
        // 加柠檬
        beverage = new Lemon(beverage);
        // 加芒果
        beverage = new Mango(beverage);
        // 加西瓜
        beverage = new Watermelon(beverage);

        // 加柠檬、加芒果、加西瓜的红茶饮料就做好了

        printLog("饮料1 ：" + beverage.description() + "--- cost: " + beverage.cost());

        // 打印结果 ：饮料：红茶,加柠檬, 加芒果, 加西瓜--- cost: 20.0
        // 红茶10块，加柠檬2块，加西瓜5块，加芒果3块，共花费了20元。
        printLog("###########");

        // 再来一杯加芒果，加西瓜，加柠檬的绿茶
        Beverage greenTea = new Mango(new Watermelon(new Lemon(new GreenTea())));
        printLog("饮料2 ：" + greenTea.description() + "--- cost: " + greenTea.cost());

    }


    private static void printLog(String s) {
        System.out.println(s);
    }
}
