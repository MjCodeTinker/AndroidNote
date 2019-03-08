package com.mj.test.module.test_utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.mj.lib.base.physical_storage.AppPathUtils;
import com.mj.test.module.R;

/**
 * Author      : MJ
 * Date        : 2019/3/8--19:05
 * Email       : miaojian@conew.com
 * Description : 测试工具类的页面
 */

public class TestUtilsActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_utils);
        tvContent = findViewById(R.id.test_utils_tv_content);
        AppPathUtils.init(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_utils_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.appPrivatePath:
                showTest(AppPathUtils.getAppPrivatePath());
                break;

            case R.id.appPrivateFilesPath:
                showTest(AppPathUtils.getAppPrivateFilesPath());
                break;

            case R.id.appPrivateCachesPath:
                showTest(AppPathUtils.getAppPrivateCachesPath());
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showTest(String text) {
        tvContent.setText(text);
    }

}
