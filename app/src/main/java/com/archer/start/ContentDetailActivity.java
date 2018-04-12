package com.archer.start;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ContentDetailActivity extends Activity {

    String title;
    private String fileName;
    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        mContentView.setVisibility(View.GONE);
        mShortAnimationDuration = 2000;
        crosssfade();
        TextView text_content = (TextView) findViewById(R.id.text_content);
        TextView text_title = (TextView) findViewById(R.id.text_title);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        text_title.setText(title);
        getFileName(title);
        text_content.setText(readText(fileName));
    }

    public void onBackClick(View v) {
        this.finish();
    }

    private void crosssfade() {

        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);

        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mLoadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    public void getFileName(String title) {
        if (title.equals("起源")) {
            fileName = "content00";
        }
        if (title.equals("定义")) {
            fileName = "content01";
        }
        if (title.equals("发展")) {
            fileName = "content02";
        }
        if (title.equals("发展趋势")) {
            fileName = "content03";
        }
        if (title.equals("用途和问题")) {
            fileName = "content04";
        }
        if (title.equals("认识误区")) {
            fileName = "content05";
        }
        if (title.equals("中国发展")) {
            fileName = "content06";
        }
        if (title.equals("中国问题")) {
            fileName = "content07";
        }
        if (title.equals("发展规划")) {
            fileName = "content08";
        }
        if (title.equals("就业前景")) {
            fileName = "content09";
        }
        if (title.equals("相关知识")) {
            fileName = "content10";
        }
        if (title.equals("传感器的定义与功能")) {
            fileName = "content11";
        }
        if (title.equals("无线起源")) {
            fileName = "content12";
        }
        if (title.equals("传感网与物联网之间的联系")) {
            fileName = "content13";
        }
        if (title.equals("M2M简介及其发展")) {
            fileName = "content14";
        }
        if (title.equals("M2M应用系统构成")) {
            fileName = "content15";
        }
        if (title.equals("M2M应用领域")) {
            fileName = "content16";
        }
        if (title.equals("云计算的基本概念")) {
            fileName = "content17";
        }
        if (title.equals("云计算的特点")) {
            fileName = "content18";
        }
        if (title.equals("云计算的应用")) {
            fileName = "content19";
        }
        if (title.equals("RFID的定义")) {
            fileName = "content20";
        }
        if (title.equals("RFID工作原理")) {
            fileName = "content21";
        }
        if (title.equals("RFID的特点")) {
            fileName = "content22";
        }
        if (title.equals("RFID产品分类")) {
            fileName = "content23";
        }
        if (title.equals("RFID的市场发展")) {
            fileName = "content24";
        }
        if (title.equals("什么是嵌入式系统")) {
            fileName = "content25";
        }
        if (title.equals("嵌入式系统组成")) {
            fileName = "content26";
        }
        if (title.equals("嵌入式系统的应用领域")) {
            fileName = "content27";
        }
        if (title.equals("嵌入式系统的发展现状与发展趋势")) {
            fileName = "content28";
        }
        if (title.equals("嵌入式软件架构类型")) {
            fileName = "content29";
        }
        if (title.equals("何为两化融合")) {
            fileName = "content30";
        }
        if (title.equals("融合的四个方面")) {
            fileName = "content31";
        }
        if (title.equals("中国现状")) {
            fileName = "content32";
        }
        if (title.equals("两化融合的升级之路")) {
            fileName = "content33";
        }
        if (title.equals("两化融合与物联网之间的关系")) {
            fileName = "content34";
        }
        if (title.equals("单片机简介")) {
            fileName = "content35";
        }
        if (title.equals("单片机的发展历史")) {
            fileName = "content36";
        }
        if (title.equals("单片机的应用范围")) {
            fileName = "content37";
        }
        if (title.equals("Java语言产生的背景")) {
            fileName = "content38";
        }
        if (title.equals("Java的组成与体系")) {
            fileName = "content39";
        }
        if (title.equals("Java基本含义与基本语法")) {
            fileName = "content40";
        }
        if (title.equals("Java语言的特点")) {
            fileName = "content41";
        }
        if (title.equals("Java语言的发展前景")) {
            fileName = "content42";
        }
        if (title.equals("java语言的相关技术")) {
            fileName = "content43";
        }
        if (title.equals("Android系统简介")) {
            fileName = "content44";
        }
        if (title.equals("Android发展历程")) {
            fileName = "content45";
        }
        if (title.equals("Android系统架构与结构")) {
            fileName = "content46";
        }
        if (title.equals("Android应用组件")) {
            fileName = "content47";
        }
        if (title.equals("Android平台优势")) {
            fileName = "content48";
        }
        if (title.equals("C语言知识概述")) {
            fileName = "content49";
        }
        if (title.equals("C语言特点")) {
            fileName = "content50";
        }
        if (title.equals("C语言组成")) {
            fileName = "content51";
        }
        if (title.equals("网络技术简介与发展历程")) {
            fileName = "content52";
        }
        if (title.equals("网络技术应用领域")) {
            fileName = "content53";
        }
        if (title.equals("网络技术研究现状")) {
            fileName = "content54";
        }
        if (title.equals("网络分类组成")) {
            fileName = "content55";
        }
        if (title.equals("网络技术的延伸")) {
            fileName = "content56";
        }
        if (title.equals("互联网定义与发展历程")) {
            fileName = "content57";
        }
        if (title.equals("互联网运行原理")) {
            fileName = "content58";
        }
        if (title.equals("物联网应用中关键技术")) {
            fileName = "content59";
        }
        if (title.equals("互联网的发展状况")) {
            fileName = "content60";
        }
        if (title.equals("网络预测与服务模式")) {
            fileName = "content61";
        }


    }

    public String readText(String fileName) {
        InputStream is = null;
        InputStreamReader isr = null;
        is = this.getClass().getClassLoader().getResourceAsStream("assets/" + fileName + ".txt");
        try {
            isr = new InputStreamReader(is, "gbk");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
