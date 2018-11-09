package com.mj.android_note.libary.adaptive;

/**
 * Author      : MJ
 * Date        : 2018/11/9--17:09
 * Email       : miaojian@conew.com
 * Description :
 */

public final class AppAdaptationImpl implements IAppAdaptation<BaseAdaptationEntity> {

    @Override
    public void beganToFit(BaseAdaptationEntity adaptationEntity) {
        if (adaptationEntity == null || adaptationEntity.getAppAdaptation() == null) {
            throw new NullPointerException("params is null...");
        }
        adaptationEntity.getAppAdaptation().beganToFit(adaptationEntity);
    }
}
