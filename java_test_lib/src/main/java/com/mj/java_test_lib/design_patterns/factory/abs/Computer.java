package com.mj.java_test_lib.design_patterns.factory.abs;

/**
 * Author      : MJ
 * Date        : 2020-02-20--18:24
 * Email       : miaojian_666@126.com
 * Description : 组装对象
 */
public class Computer {

    public Cpu cpu;
    public MainBoard mainBoard;

    public Computer(Cpu cpu, MainBoard mainBoard) {
        this.cpu = cpu;
        this.mainBoard = mainBoard;
    }

    public String description() {
        return (cpu != null ? cpu.name : "") + "，" + (mainBoard != null ? mainBoard.name : "");
    }

}
