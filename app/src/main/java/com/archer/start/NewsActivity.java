package com.archer.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.archer.ConstantUtils;
import com.archer.adapter.NewsAdapter;
import com.archer.model.NewsBean;
import com.archer.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity implements OnItemClickListener {

    private ListView lvNews;
    private NewsAdapter adapter;
    private List<NewsBean> newsList;
    private ProgressBar newsProgressBar;

    private Handler getNewsHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            Log.d("xys","-----------> "+jsonData);
            System.out.println(jsonData);
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("newss");
                Log.d("xys","==========> "+jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("des");
                    String time = object.getString("time");
                    String content_url = object.getString("news_url");
                    String pic_url = object.getString("icon_url");
                    newsList.add(new NewsBean(title, desc, time, content_url, pic_url));
                }
                adapter.notifyDataSetChanged();
                newsProgressBar.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsProgressBar = (ProgressBar) findViewById(R.id.newsProgressBar);
        lvNews = (ListView) findViewById(R.id.lvNews);
        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this, newsList);
        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);
        HttpUtils.getNewsJSON(ConstantUtils.getActionUrl(ConstantUtils.GET_NEWS_URL), getNewsHandler);

    }

    public void onRefreshBtnClick(View v) {
        newsList.clear();
        Toast.makeText(this, "正在刷新", Toast.LENGTH_SHORT).show();
        HttpUtils.getNewsJSON(ConstantUtils.getActionUrl(ConstantUtils.GET_NEWS_URL), getNewsHandler);
        adapter.notifyDataSetChanged();
    }

    public void newsHeader(View v) {
        lvNews.setSelection(0);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        NewsBean news = newsList.get(position);
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("content_url", news.getContent_url());
        intent.putExtra("title", news.getTitle());
        startActivity(intent);
    }

}
