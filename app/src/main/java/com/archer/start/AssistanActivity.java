package com.archer.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.archer.model.MessageBean;
import com.archer.adapter.ChatMessageAdapter;
import com.archer.util.ChatHttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssistanActivity extends Activity implements View.OnClickListener {
    ListView list_msg;
    TextView send_msg,receive_msg;
    Button send_btn;
    String msg;
    private ChatMessageAdapter mAdapter;
    List<MessageBean> data;
    EditText send_text;
    Boolean flag=true;
    public static int flag1;
    String username;
    //用于更新子线程改变的控件
    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            //等待接收子线程的数据返回
            MessageBean message=(MessageBean) msg.obj;
            data.add(message);
            mAdapter.notifyDataSetChanged();
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);

        list_msg=(ListView) findViewById(R.id.list_msg1);
        send_text=(EditText) findViewById(R.id.send_text);
        send_btn=(Button) findViewById(R.id.send_btn);
    	/*Bundle bundle=getIntent().getExtras();
		username=bundle.getString("index");*/

        send_btn.setOnClickListener(this);
        data=new ArrayList<MessageBean>();
        initDatas();

    }
    private void initDatas() {
        data=new ArrayList<MessageBean>();
        mAdapter=new ChatMessageAdapter(this, data);
        list_msg.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                final String text = send_text.getText().toString();
                if(text==null){
                    Toast.makeText(this,"发送信息不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    MessageBean message01 = new MessageBean();
                    message01.setMsg(text);
                    message01.setType(MessageBean.Type.INCOMING);
                    message01.setDate(new Date());
                    data.add(message01);
                }

                //网络层操作不应该在 子线程,子线程中不能更新控件
                new Thread() {
                    public void run() {
                        MessageBean message = ChatHttpUtils.sendMessage(text);
                        Message m = Message.obtain();
                        m.obj = message;
                        mHandler.sendMessage(m);
                    }

                    ;
                }.start();
                send_text.setText("");
                break;
            default:break;
        }
    }
    public void getFocusable(View view){
        send_text.setFocusable(true);
        send_text.setFocusableInTouchMode(true);

    }
}
