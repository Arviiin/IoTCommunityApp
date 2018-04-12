package com.archer.start;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.archer.ConstantUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {
    private EditText userName = null;
    private EditText password = null;
    private Button login = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {//当服务器返回给客户端的标记为1时，说明登陆成功
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT)
                        .show();
                SharedPreferences mySharedPreferences = getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("name", userName.getText().toString());
                editor.apply();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initListener();
    }

    /*
 * login页面参数初始化，获取login页面对应的元素
 */
    private void init() {
        userName = (EditText) this.findViewById(R.id.loginPageUsername);
        password = (EditText) this.findViewById(R.id.loginPagePasswd);
        login = (Button) this.findViewById(R.id.loginPageLoginBtn);
    }

    /*
     * 对login上的页面元素设置监听
     */
    private void initListener() {
        this.login.setOnClickListener(new LoginListener());
        //this.register.setOnClickListener(new ButtonRegister());
    }

    @SuppressWarnings("deprecation")
    private class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            new Thread() {
                public void run() {
                    HttpClient client = new DefaultHttpClient();
                    List<NameValuePair> list = new ArrayList<>();
                    NameValuePair pair = new BasicNameValuePair("index", "0");
                    list.add(pair);
                    NameValuePair pair1 = new BasicNameValuePair("username", userName.getText().toString());
                    NameValuePair pair2 = new BasicNameValuePair("password", password.getText().toString());
                    list.add(pair1);
                    list.add(pair2);
                    try {
                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");




                        HttpPost post = new HttpPost(ConstantUtils.getActionUrl2(ConstantUtils.LOGIN_URL));
                        //现在我不采用调用ConstantUtils类中的静态方法来获取要访问的地址,这里我选择直接写死的地址要访问服务端
                        //因为服务端我们采用了两个服务器
                        //HttpPost post = new HttpPost("http://172.25.13.242:8080/server/Servlet");
                        //HttpPost post = new HttpPost("http://192.168.191.1:8080/server/Servlet");
                        //登录时Servlet在起作用


                        post.setEntity(entity);
                        HttpResponse response = client.execute(post);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            InputStream in = response.getEntity().getContent();
                            handler.sendEmptyMessage(in.read());//将服务器返回给客户端的标记直接传给handler
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }.start();
        }


    }

    public void onBackBtnClick(View v) {

        finish();
    }

    public void register(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
