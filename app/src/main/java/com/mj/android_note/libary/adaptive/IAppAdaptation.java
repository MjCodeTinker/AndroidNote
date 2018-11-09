package com.mj.android_note.libary.adaptive;

/**
 * Author      : MJ
 * Date        : 2018/11/9--16:59
 * Email       : miaojian@conew.com
 * Description : app适配的抽象
 */

public interface IAppAdaptation<T extends BaseAdaptationEntity> {
    /**
     * 适配方案
     *
     * @param adaptationEntity 适配的实体类
     */
    void beganToFit(T adaptationEntity);
}
