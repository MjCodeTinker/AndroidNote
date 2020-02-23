package com.mj.java_test_lib.design_patterns.strategy;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:07
 * Email       : miaojian_666@126.com
 * Description :
 */
public class DrawStrategy {
    private StrategyPaint strategyPaint;

    public DrawStrategy(StrategyPaint strategyPaint) {
        this.strategyPaint = strategyPaint;
    }

    public void draw() {
        strategyPaint.draw();
    }

}
