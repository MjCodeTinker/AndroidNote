package com.mj.android_note.Tools.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : MJ
 * Date        : 2019-08-05--00:13
 * Email       : miaojian_666@126.com
 * Description : 消息的包装类
 */
public class MessageWrapper {

    // 正常聊天标题的名字
    public String ChatTitleName;

    // 聊天ID
    public String ChatId;

    //具体的聊天内容数组
    public List<ChatMessageEntity> MessageList = new ArrayList<>();
}
