package com.archer.start;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.archer.note.NoteActivity;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    private String[] m_tabTitle = new String[]{"新贴", "主页", "安全资讯", "设置"};
    private Class<?>[] m_tabIntent = new Class<?>[]{NewsActivity.class,
            ContentActivity.class, ForumActivity.class, SettingPage.class};

    private int[] m_tabIcon = new int[]{R.mipmap.collections_view_as_list,
            R.mipmap.collections_view_as_grid, R.mipmap.coffee,
            R.mipmap.action_settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_forum);
        initTab();

    }

    private void initTab() {
        TabHost tabHost = getTabHost();
        for (int i = 0; i < this.m_tabTitle.length; i++) {
            String title = this.m_tabTitle[i];
            Intent intent = new Intent(this, m_tabIntent[i]);
            @SuppressLint("InflateParams")
            View tab = getLayoutInflater().inflate(R.layout.tab_forum, null);
            ImageView imgView = (ImageView) tab.findViewById(R.id.tabIcon);
            imgView.setImageResource(m_tabIcon[i]);
            TabSpec spec = tabHost.newTabSpec(title).setIndicator(tab)
                    .setContent(intent);
            tabHost.addTab(spec);
        }
    }

    public void openMenu02(View v) {
        Intent intent = new Intent();
        intent.setClass(this, WebActivity.class);
//        intent.putExtra("index", "http://3g.163.com/touch/tech/?from=index.sitemap");
        intent.putExtra("index", "http://3g.163.com/touch/tech/subchannel/all?from=index.sitemap&dataversion=A&version=v_standard");
//        intent.putExtra("index", "https://www.github.com");


        startActivity(intent);
    }

    public void openMenu03(View v) {
      /*  Intent intent=new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra("index", "1");
        startActivity(intent);*/
    }

    public void openMenu04(View v) {
      /*  Intent intent=new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra("index", "2");
        startActivity(intent);*/
    }

    public void openMenu07(View v) {
        Intent intent = new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra("index", "http://bbs.csdn.net/home");
        startActivity(intent);
    }

    public void openMenu08(View v) {
        Intent intent = new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra("index",
                "http://m.baidu.com/ssid=0/from=1000376a/bd_page_type=1/uid=0/pu=sz%401321_1001%2Cta%40utouch_2_5.1_3_537/news?idx=30000&itj=32#index/info:%E7%A7%91%E6%8A%80");
        startActivity(intent);
    }

    public void openMenu09(View v) {
        Intent intent = new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra("index", "http://m.baidu.com/ssid=0/from=1000376a/bd_page_type=1/uid=0/pu=sz%401321_1001%2Cta%40utouch_2_5.1_3_537/news?idx=30000&itj=32#index/info:%E4%BA%92%E8%81%94%E7%BD%91");
        startActivity(intent);
    }

    public void openNotePadPage(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
        // Toast.makeText(this,"dianji",Toast.LENGTH_SHORT).show();
    }

    public void openZhuShouPage(View v) {

        Intent intent = new Intent(this, AssistanActivity.class);
        startActivity(intent);
    }


}
