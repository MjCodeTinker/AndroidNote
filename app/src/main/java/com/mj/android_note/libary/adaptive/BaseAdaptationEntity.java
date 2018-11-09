package com.mj.android_note.libary.adaptive;

/**
 * Author      : MJ
 * Date        : 2018/11/9--17:01
 * Email       : miaojian@conew.com
 * Description : 适配实体的基类
 */

public class BaseAdaptationEntity {

    private IAppAdaptation<? super BaseAdaptationEntity> appAdaptation;

    public IAppAdaptation<? super BaseAdaptationEntity> getAppAdaptation() {
        return appAdaptation;
    }
    public void setAppAdaptation(IAppAdaptation<? super BaseAdaptationEntity> appAdaptation) {
        this.appAdaptation = appAdaptation;
    }

}
