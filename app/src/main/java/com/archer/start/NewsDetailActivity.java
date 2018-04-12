package com.archer.start;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {

    private WebView webView;
    private ProgressBar pb_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        TextView title = (TextView) findViewById(R.id.text_title);
        String content_url = getIntent().getStringExtra("content_url");
        String te = getIntent().getStringExtra("title");
        title.setText(te);
        webView = (WebView) findViewById(R.id.webview);
        pb_webview = (ProgressBar) findViewById(R.id.pb_webview);
        Log.d("xys","-----------> "+content_url);

        this.enableWebSetting();
        webView.loadUrl(content_url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void enableWebSetting() {
        //enableJavaScript
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //enableCaching
        this.webView.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //enableCustomClients
        this.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                NewsDetailActivity.this.setProgress(newProgress);
                setProgress(newProgress * 1000);
                if (newProgress >= 80) {
                    NewsDetailActivity.this.pb_webview.setVisibility(View.GONE);
                } else {
                    NewsDetailActivity.this.pb_webview.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        //enableAdjust
        this.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.webView.getSettings().setLoadWithOverviewMode(true);
        //zoomedOut
        this.webView.getSettings().setLoadWithOverviewMode(true);
        this.webView.getSettings().setUseWideViewPort(true);
        this.webView.getSettings().setSupportZoom(true);
    }



    public void onBackClick(View v) {
        finish();
    }
}
