package com.mj.android_note.libary.adaptive;

/**
 * Author      : MJ
 * Date        : 2018/11/9--17:33
 * Email       : miaojian@conew.com
 * Description :
 */

public class AppAdaptationManager {

    public static IAppAdaptation<? extends BaseAdaptationEntity> getSystemSettingPageImpl() {
        IAppAdaptation<BaseAdaptationEntity> appAdaptation = new AppAdaptationImpl();
//        BaseAdaptationEntity baseAdaptationEntity = new BaseAdaptationEntity();
//        baseAdaptationEntity.setAppAdaptation(new StartSystemSettingPageImpl());

        return appAdaptation;
    }
}
