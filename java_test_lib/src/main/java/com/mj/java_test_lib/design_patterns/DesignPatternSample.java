package com.mj.java_test_lib.design_patterns;

import com.mj.java_test_lib.design_patterns.bridge.Circle;
import com.mj.java_test_lib.design_patterns.bridge.GreenPen;
import com.mj.java_test_lib.design_patterns.bridge.RectAngle;
import com.mj.java_test_lib.design_patterns.bridge.RedPen;
import com.mj.java_test_lib.design_patterns.bridge.Shape;
import com.mj.java_test_lib.design_patterns.decorator.component.Beverage;
import com.mj.java_test_lib.design_patterns.decorator.component.GreenTea;
import com.mj.java_test_lib.design_patterns.decorator.component.RedTea;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Lemon;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Mango;
import com.mj.java_test_lib.design_patterns.decorator.decorator_component.Watermelon;
import com.mj.java_test_lib.design_patterns.delegate.delegate_parttern.IUser;
import com.mj.java_test_lib.design_patterns.delegate.delegate_parttern.UserImpl;
import com.mj.java_test_lib.design_patterns.delegate.delegate_parttern.UserProxy;
import com.mj.java_test_lib.design_patterns.delegate.proxy.UserDynamicProxy;
import com.mj.java_test_lib.design_patterns.factory.abs.AmdFactory;
import com.mj.java_test_lib.design_patterns.factory.abs.Computer;
import com.mj.java_test_lib.design_patterns.factory.abs.ComputerFactory;
import com.mj.java_test_lib.design_patterns.factory.abs.IntelFactory;
import com.mj.java_test_lib.design_patterns.factory.real.AmericanFoodFactory;
import com.mj.java_test_lib.design_patterns.factory.real.ChineseFoodFactory;
import com.mj.java_test_lib.design_patterns.factory.real.FoodFactory;
import com.mj.java_test_lib.design_patterns.factory.simple.StaticFactory;
import com.mj.java_test_lib.design_patterns.responsebility.AbstractLeaveProcess;
import com.mj.java_test_lib.design_patterns.responsebility.GroupLeader;
import com.mj.java_test_lib.design_patterns.responsebility.Hr;
import com.mj.java_test_lib.design_patterns.responsebility.Vp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author      : MJ
 * Date        : 2020-02-20--17:26
 * Email       : miaojian_666@126.com
 * Description : 设计模式，可运行的java入口
 */
public class DesignPatternSample {


    public static void main(String[] args) {

        // ########工厂模式########
        // 1.简单工厂模式
        printLog("###静态工厂模式###");
        printLog("factory type = 0:" + StaticFactory.createA(0).getName());
        printLog("factory type = 1:" + StaticFactory.createA(1).getName());

        // 2.工厂模式
        printLog("###工厂模式###");
        FoodFactory chineseFoodFactory = new ChineseFoodFactory();
        FoodFactory americanFoodFactory = new AmericanFoodFactory();
        printLog(chineseFoodFactory.makeFood().name);
        printLog(americanFoodFactory.makeFood().name);

        // 3.抽象工厂模式
        printLog("###抽象工厂模式###");
        ComputerFactory intelComputerFactory = new IntelFactory();
        ComputerFactory amdComputerFactory = new AmdFactory();
        Computer intelComputer = new Computer(intelComputerFactory.makeCpu(), intelComputerFactory.makeMainBoard());
        Computer amdComputer = new Computer(amdComputerFactory.makeCpu(), amdComputerFactory.makeMainBoard());
        printLog(intelComputer.description());
        printLog(amdComputer.description());

        // 4.代理模式
        printLog("###代理模式###");
        UserProxy userProxy = new UserProxy(new UserImpl());
        printLog("name : " + userProxy.getUserName());

        // 5.动态代理
        printLog("###动态代理###");
        UserImpl originUser = new UserImpl();
        IUser iUser = (IUser) Proxy.newProxyInstance(
                originUser.getClass().getClassLoader(),
                originUser.getClass().getInterfaces(),
                new UserDynamicProxy(originUser));

        printLog("user .getName " + iUser.getUserName());

        // 6. 桥梁模式
        printLog("###桥梁模式###");

        Shape redCircle = new Circle(5, new RedPen());
        Shape greenRectangle = new RectAngle(4, 8, new GreenPen());
        redCircle.draw();
        greenRectangle.draw();

        // 7.###### 装饰者模式 ######
        printLog("###装饰者模式###");
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

        // 8.责任链模式
        printLog("###责任链模式###");
        // 拿请假流程来实现案例，比如一个员工要请假需要经过组长，vp,hr三个步骤
        AbstractLeaveProcess groupLeader = new GroupLeader();
        AbstractLeaveProcess vp = new Vp();
        AbstractLeaveProcess hr = new Hr();
        groupLeader.setAbstractLeaveProcess(vp);
        vp.setAbstractLeaveProcess(hr);

        // 员工最开始直接找组长请假了，然后整个流程都会一步一步走完。
        printLog("请假结果 ： " + groupLeader.apply());

    }


    private static void printLog(String s) {
        System.out.println(s);
    }

}
