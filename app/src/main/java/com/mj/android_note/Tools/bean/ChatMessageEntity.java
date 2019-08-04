package com.mj.android_note.Tools.bean;

/**
 * Author      : MJ
 * Date        : 2019-08-05--00:13
 * Email       : miaojian_666@126.com
 * Description : 单条消息内容
 */
public class ChatMessageEntity {

    // 本条消息的id
    public int MessageId;

    // 消息
    public String Message;

    // 角色名
    public String RoleName;

    // 真实名字
    public String RealName;

    // 上一条消息的Id
    public int ParentMessageId;

    //是否是主角
    public boolean IsProtagonist;

    // 下一条消息的ID，此字段适用于多条可选消息，对应同一条消息的回复，0为默认值
    public int NextMessageId = 0;

    // 当前消息隔多久展示出来,单位为秒
    public float MessageInterval = 1.2f;

    // 判断人物介绍是否结束,目前此字段只用于c_02_04_00 中的场景对话
    public boolean IntroduceOver = false;

    // 需要点击在展现下一条消息
    public boolean NeedClickShowNextMessage = false;

    // 消息类型 0 代表文本内容、 1代表图片内容、2代表旁白对话内容
    public int MessageType = 0;

    // 聊天图片地址
    public String MessageImagePath;

    // 预先设置的文本
    public String PreviousMessage;

    // 回应的角色id （只对应于主角的消息会有此字段）
    public String ReplyRoleId;

    // 回应的角色名字 （只对应于主角的消息会有此字段）
    public String ReplyRoleName;

    // 加多少心动值的数量 （只对应于主角的消息会有此字段）
    public int HeartBeatNum;

    // 获取聊天消息的类型
//    public ChatMessageType GetChatMessageType()
//    {
//        switch (MessageType)
//        {
//            case 1:
//
//                return ChatMessageType.ImageType;
//
//            case 2:
//
//                return ChatMessageType.Narrator;
//
//            default:
//
//                return ChatMessageType.Text;
//        }
//    }

//    // 获取图片的全路径
//    public string GetMessageImageFullPath()
//    {
//        return "UI/Chat/EmotionImage/" + MessageImagePath;
//    }
}
