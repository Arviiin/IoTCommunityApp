package com.archer.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.archer.ConstantUtils;

import org.apache.http.HttpEntity;
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


public class AddForumItem extends Activity {
    private EditText title;
    private EditText content;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //等于1是说明接收到了注册成功的信息，当注册成功时，服务器会返回1给客户端
                Toast.makeText(AddForumItem.this, "发布成功", Toast.LENGTH_SHORT)
                        .show();
                finish();
            } else {
                Toast.makeText(AddForumItem.this, "发布失败", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);
        title = (EditText) findViewById(R.id.add_forum_title);
        content = (EditText) findViewById(R.id.add_forum_content);
    }

    public void onSubmitBtnClick(View v) {

        excuteRelease();
        finish();
    }

    public void onBackBtnClick(View v) {
        finish();
    }

    public void excuteRelease() {
        new Thread() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                super.run();
                HttpClient client = new DefaultHttpClient();
                List<NameValuePair> list = new ArrayList<>();
                NameValuePair pair = new BasicNameValuePair("index", "3");
                list.add(pair);
                NameValuePair pair1 = new BasicNameValuePair("name", title.getText().toString());
                NameValuePair pair2 = new BasicNameValuePair("description", content.getText().toString());
                list.add(pair1);
                list.add(pair2);
                try {
                    HttpEntity entity = new UrlEncodedFormEntity(list, "UTF-8");

                    //HttpPost post = new HttpPost(ConstantUtils.getActionUrl(ConstantUtils.LOGIN_URL));
                    //现在我不采用调用ConstantUtils类中的静态方法来获取要访问的地址,这里我选择直接写死的地址要访问服务端
                    //因为服务端我们采用了两个服务器
                    //HttpPost post = new HttpPost("http://172.25.13.242:8080/server/Servlet");
                    HttpPost post = new HttpPost(ConstantUtils.getActionUrl2(ConstantUtils.LOGIN_URL));
                    //HttpPost post = new HttpPost("http://192.168.191.1:8080/server/Servlet");

                    post.setEntity(entity);
                    HttpResponse response = client.execute(post);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        InputStream in = response.getEntity().getContent();
                        handler.sendEmptyMessage(in.read());//将服务器端返回给客户端的标记直接传输给handler
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

}
