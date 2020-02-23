package com.mj.java_test_lib.design_patterns.responsebility;

/**
 * Author      : MJ
 * Date        : 2020-02-23--16:26
 * Email       : miaojian_666@126.com
 * Description :
 */
public class GroupLeader extends AbstractLeaveProcess {
    @Override
    public String apply() {
        return "组长请假通过->" + (getAbstractLeaveProcess() != null ? getAbstractLeaveProcess().apply() : "流程结束");
    }
}
