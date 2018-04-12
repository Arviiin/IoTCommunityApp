package com.archer.start;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.archer.adapter.ForumAdapter;
import com.archer.model.FroumBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView list_msg;
    FroumBean froumBean;
    int id = 10;

    List<FroumBean> froumBeanList;
    ForumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        list_msg = (ListView) findViewById(R.id.list_msg);
        list_msg.setOnItemClickListener(this);
       // new NewsAsyncTask().execute(ConstantUtils.getActionUrl(ConstantUtils.GET_FOURM_URL));
    }

    /*
    * 将url对应的JSON格式数据转化为封装的newBean对象*/
    private List<FroumBean> getJsonData(String url) {
        froumBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                Log.d("xys","==========> "+jsonArray.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    froumBean = new FroumBean();
                    froumBean.newsIconUrl = jsonObject.getString("picSmall");
                    froumBean.newsContent = jsonObject.getString("description");
                    froumBean.newsTitle = jsonObject.getString("name");
                    froumBeanList.add(froumBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return froumBeanList;
    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line;
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void onRefresh(View v) {
        froumBeanList.clear();
        Toast.makeText(this, "正在刷新", Toast.LENGTH_SHORT).show();



       // new NewsAsyncTask().execute(ConstantUtils.getActionUrl(ConstantUtils.GET_FOURM_URL));
        //http://192.168.191.1:8080/itheima74/servlet/GetForumServlet
        new NewsAsyncTask().execute("http://192.168.191.1:8080/itheima74/servlet/GetForumServlet");





        adapter.notifyDataSetChanged();
    }

    public void onAddForumItem(View v) {
        Intent intent = new Intent(this, AddForumItem.class);
        startActivity(intent);
    }

    public void forumHeader(View v) {
        list_msg.setSelection(0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FroumBean news = froumBeanList.get(position);
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("content_url", news.newsContent);
        intent.putExtra("title", news.newsTitle);
        startActivity(intent);
    }

    /*
    实现网络的异步访问*/
    private class NewsAsyncTask extends AsyncTask<String, Void, List<FroumBean>> {

        @Override
        protected List<FroumBean> doInBackground(String... params) {

            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<FroumBean> froumBeen) {
            super.onPostExecute(froumBeen);
            if (adapter == null) {
                adapter = new ForumAdapter(ForumActivity.this, froumBeen, list_msg);
                list_msg.setAdapter(adapter);
            } else {
                adapter.onDateChanged(froumBeen);
            }
        }
    }


}
