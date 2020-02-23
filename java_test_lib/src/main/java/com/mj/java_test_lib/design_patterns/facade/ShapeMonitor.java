package com.mj.java_test_lib.design_patterns.facade;

/**
 * Author      : MJ
 * Date        : 2020-02-23--00:29
 * Email       : miaojian_666@126.com
 * Description :
 */
public class ShapeMonitor {

    private IFacadeShape circle;
    private IFacadeShape rectangle;
    private IFacadeShape square;

    public void drawCircle() {
        if (circle == null) {
            circle = new CircleShape();
        }
        circle.draw();
    }

    public void drawRectAngle() {
        if (rectangle == null) {
            rectangle = new RectAngleShape();
        }
        rectangle.draw();
    }

    public void drawSquare() {
        if (square == null) {
            square = new SquareShape();
        }
        square.draw();
    }
}
