package com.mj.android_note.Tools;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.mj.android_note.R;
import com.mj.android_note.Tools.bean.MessageWrapper;
import com.mj.android_note.base.BaseActivity;

/**
 * Author      : MJ
 * Date        : 2019-08-05--00:17
 * Email       : miaojian_666@126.com
 * Description : 聊天json 生成页面
 */
public class ChatJsonToolsActivity extends BaseActivity {


    private MessageWrapper messageWrapper = new MessageWrapper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_json_tools);
        Gson g = new Gson();

        String json = g.toJson(messageWrapper);


    }
}
