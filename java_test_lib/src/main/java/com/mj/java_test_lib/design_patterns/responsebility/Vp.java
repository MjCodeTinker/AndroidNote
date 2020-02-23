package com.mj.java_test_lib.design_patterns.responsebility;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:31
 * Email       : miaojian_666@126.com
 * Description :
 */
public class Vp extends AbstractLeaveProcess {
    @Override
    public String apply() {
        return "VP请假通过->" + (getAbstractLeaveProcess() != null ? getAbstractLeaveProcess().apply() : "流程结束");
    }
}
