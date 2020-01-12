package com.mj.android_note.ui.activity.butter_knife;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mj.android_note.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author      : MJ
 * Date        : 2019-11-22--23:27
 * Email       : miaojian_666@126.com
 * Description :
 */
public class TestButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.activity_test_butter_knife_tv)
    TextView tv;

    // bind
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_butter_knife);
        bind = ButterKnife.bind(this);

        tv.setText(String.format("%s","i am miaojian"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }
}
