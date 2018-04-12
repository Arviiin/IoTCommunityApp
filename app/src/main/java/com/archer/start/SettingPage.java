package com.archer.start;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SettingPage extends Activity {
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        userName = (TextView) findViewById(R.id.settingPageLoginUserName);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        userName.setText(name);
    }

    //用户登录
    public void onLoginItemClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

    public void settingHeader(View v) {

    }

    //反馈
    public void onFeedbackItemClick(View v) {
        Toast.makeText(SettingPage.this, "未安装邮件客户端", Toast.LENGTH_SHORT).show();
    }

    public void onCheckUpdateItemClick(View v) {
        Toast.makeText(SettingPage.this, "已是最新版本", Toast.LENGTH_SHORT).show();
    }

    public void aboutThisApp(View v) {
        Intent intent = new Intent();
        intent.setClass(this, AboutActivity.class);
        startActivity(intent);
    }

}
