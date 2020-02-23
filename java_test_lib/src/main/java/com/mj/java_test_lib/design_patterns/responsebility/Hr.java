package com.mj.java_test_lib.design_patterns.responsebility;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:32
 * Email       : miaojian_666@126.com
 * Description :
 */
public class Hr extends AbstractLeaveProcess {
    @Override
    public String apply() {
        return "Hr汇总结束->" + (getAbstractLeaveProcess() != null ? getAbstractLeaveProcess().apply() : "流程结束");
    }
}
