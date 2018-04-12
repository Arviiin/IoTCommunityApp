package com.archer.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.model.NewsBean;
import com.archer.util.HttpUtils;
import com.archer.start.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<NewsBean> newsList;

    public NewsAdapter(Context context, List<NewsBean> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public NewsBean getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
        NewsBean news = newsList.get(position);
        tvTitle.setText(news.getTitle());
        tvDesc.setText(news.getDesc());
        if (news.getTime() != null) {
            tvTime.setText(news.getTime());
        }
        String pic_url = news.getPic_url();
        //HttpUtils.setPicBitmap(ivPic, pic_url);
        Glide.with(context).load(pic_url).into(ivPic);
        return convertView;
    }


}
