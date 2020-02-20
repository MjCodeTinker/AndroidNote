package com.mj.java_test_lib.design_patterns.factory.abs;

/**
 * Author      : MJ
 * Date        : 2020-02-20--18:31
 * Email       : miaojian_666@126.com
 * Description :
 */
public class IntelFactory implements ComputerFactory{

    @Override
    public Cpu makeCpu() {
        Cpu cpu = new Cpu();
        cpu.name = "我是intel的cpu";
        return cpu;
    }

    @Override
    public MainBoard makeMainBoard() {
        MainBoard mainBoard = new MainBoard();
        mainBoard.name = "我是intel的主板";
        return mainBoard;
    }
}
