package com.mj.test.module.test_message_train;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mj.lib.base.communication.app_inner.MessageTrain;
import com.mj.lib.base.communication.app_inner.annotation.Subscriber;
import com.mj.test.module.R;
import com.mj.test.module.test_message_train.bean.FragmentMsg;

/**
 * Author      : MJ
 * Date        : 2019/3/7--14:01
 * Email       : miaojian@conew.com
 * Description :
 */

public class TestMessageTrainFragment extends Fragment {

    public static TestMessageTrainFragment newInstance() {
        return new TestMessageTrainFragment();
    }

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MessageTrain.getDefault().register(this);
        return inflater.inflate(R.layout.fragment_test_message_train, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.test_fragment_message_train);
    }

    @Subscriber
    public void showMessage(FragmentMsg FragmentMsg) {
        textView.setText(FragmentMsg.msg);
    }

    @Override
    public void onDestroy() {
        MessageTrain.getDefault().unRegister(this);
        super.onDestroy();
    }
}
