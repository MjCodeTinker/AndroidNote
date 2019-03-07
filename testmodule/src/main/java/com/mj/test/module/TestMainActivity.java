package com.mj.test.module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.mj.test.module.test_message_train.TestMessageTrainActivity;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        bindBtn(R.id.test_main_btn_message_train, TestMessageTrainActivity.class);
    }

    private void bindBtn(int id, final Class<?> testMessageTrainActivityClass) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, testMessageTrainActivityClass);
                startActivity(intent);
            }
        });
    }
}
