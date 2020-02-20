package com.mj.java_test_lib.design_patterns.factory.real;

/**
 * Author      : MJ
 * Date        : 2020-02-20--17:54
 * Email       : miaojian_666@126.com
 * Description : 美国进口食物
 */
public class AmericanFoodFactory implements FoodFactory {
    @Override
    public Food makeFood() {
        // 可以从这里衍生出很多种美国食物
        Food food = new Food();
        food.name = "我是美国进口的食物";
        return food;
    }
}
