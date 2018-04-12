package com.archer.start;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentActivity extends Activity {
    ExpandableListView mainlistview = null;
    List<String> parent = null;
    Map<String, List<String>> map = null;
    String[] title = new String[]{"起源", "定义", "发展", "发展趋势", "用途和问题", "认识误区"
            , "中国发展", "中国问题", "发展规划", "就业前景", "相关知识"};
    String[] content02 = new String[]{"传感器的定义与功能", "无线起源", "传感网与物联网之间的联系"};
    String[] content03 = new String[]{"M2M简介及其发展", "M2M应用系统构成", "M2M应用领域"};
    String[] content04 = new String[]{"云计算的基本概念", "云计算的特点", "云计算的应用"};
    String[] content05 = new String[]{"RFID的定义", "RFID工作原理", "RFID的特点", "RFID产品分类", "RFID的市场发展"};
    String[] content06 = new String[]{"什么是嵌入式系统", "嵌入式系统组成", "嵌入式系统的应用领域"
            , "嵌入式系统的发展现状与发展趋势", "嵌入式软件架构类型"};
    String[] content07 = new String[]{"何为两化融合", "融合的四个方面", "中国现状", "两化融合的升级之路"
            , "两化融合与物联网之间的关系"};
    String[] content08 = new String[]{"单片机简介", "单片机的发展历史", "单片机的应用范围"};
    String[] content09 = new String[]{"Java语言产生的背景", "Java的组成与体系", "Java基本含义与基本语法"
            , "Java语言的特点", "Java语言的发展前景", "java语言的相关技术"};
    String[] content10 = new String[]{"Android系统简介", "Android发展历程", "Android系统架构与结构"
            , "Android应用组件", "Android平台优势"};
    String[] content11 = new String[]{"C语言知识概述", "C语言特点", "C语言组成"};
    String[] content12 = new String[]{"网络技术简介与发展历程", "网络技术应用领域", "网络技术研究现状"
            , "网络分类组成", "网络技术的延伸"};
    String[] content13 = new String[]{"互联网定义与发展历程", "互联网运行原理", "物联网应用中关键技术"
            , "互联网的发展状况", "网络预测与服务模式"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mainlistview = (ExpandableListView) this
                .findViewById(R.id.main_expandablelistview);

        initData();
        mainlistview.setAdapter(new MyAdapter());
        mainlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String text = parent.getChildAt(childPosition).toString();
                if (text.equals("child1-1")) {
                    Toast.makeText(ContentActivity.this, text, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    // 初始化数据
    public void initData() {
        parent = new ArrayList<>();
        parent.add("物联网简介");
        parent.add("传感网");
        parent.add("M2M");
        parent.add("云计算");
        parent.add("RFID");
        parent.add("嵌入式系统");
        parent.add("两化融合");
        parent.add("单片机");
        parent.add("java知识概述");
        parent.add("android开发知识概述");
        parent.add("C语言知识概述");
        parent.add("网络技术");
        parent.add("互联网");
        map = new HashMap<>();

        List<String> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(title).subList(0, 11));
        map.put("物联网简介", list1);

        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2, content02);
        map.put("传感网", list2);

        List<String> list3 = new ArrayList<>();
        Collections.addAll(list3, content03);
        map.put("M2M", list3);
        List<String> list4 = new ArrayList<>();
        Collections.addAll(list4, content04);
        map.put("云计算", list4);

        List<String> list5 = new ArrayList<>();
        Collections.addAll(list5, content05);
        map.put("RFID", list5);

        List<String> list6 = new ArrayList<>();
        Collections.addAll(list6, content06);
        map.put("嵌入式系统", list6);

        List<String> list7 = new ArrayList<>();
        Collections.addAll(list7, content07);
        map.put("两化融合", list7);

        List<String> list8 = new ArrayList<>();
        Collections.addAll(list8, content08);
        map.put("单片机", list8);

        List<String> list9 = new ArrayList<>();
        Collections.addAll(list9, content09);
        map.put("java知识概述", list9);

        List<String> list10 = new ArrayList<>();
        Collections.addAll(list10, content10);
        map.put("android开发知识概述", list10);

        List<String> list11 = new ArrayList<>();
        Collections.addAll(list11, content11);
        map.put("C语言知识概述", list11);
        List<String> list12 = new ArrayList<>();
        Collections.addAll(list12, content12);
        map.put("网络技术", list12);
        List<String> list13 = new ArrayList<>();
        Collections.addAll(list13, content13);
        map.put("互联网", list13);


    }

    public void contentHeader(View v) {
        mainlistview.setSelection(0);
    }

    private class MyAdapter extends BaseExpandableListAdapter {

        //得到子item需要关联的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @SuppressLint("InflateParams")
        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, final ViewGroup parent) {
            String key = ContentActivity.this.parent.get(groupPosition);
            final String info = map.get(key).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) ContentActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_content_children, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.second_textview);

            tv.setText(info);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String text=parent.getChildAt(childPosition).toString();
                    Intent intent = new Intent(ContentActivity.this, ContentDetailActivity.class);
                    intent.putExtra("title", info);
                    startActivity(intent);
                    // Toast.makeText(ContentActivity.this, info, Toast.LENGTH_LONG).show();
                }
            });
            return tv;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            return map.get(key).size();
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //设置父item组件
        @SuppressLint("InflateParams")
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) ContentActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_content_parent, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.parent_textview);
            tv.setText(ContentActivity.this.parent.get(groupPosition));
            return tv;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }
}
