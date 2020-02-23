package com.mj.java_test_lib.design_patterns.responsebility;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:23
 * Email       : miaojian_666@126.com
 * Description :
 */
public abstract class AbstractLeaveProcess {

    // 处理请假流程
    public abstract String apply();

    // 下一位矗立着
    private AbstractLeaveProcess nextProcessor;

    public AbstractLeaveProcess getAbstractLeaveProcess() {
        return nextProcessor;
    }

    public void setAbstractLeaveProcess(AbstractLeaveProcess nextProcessor) {
        this.nextProcessor = nextProcessor;
    }
}
