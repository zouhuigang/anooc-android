package com.anooc.android.md.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.anooc.android.md.R;
import com.anooc.android.md.ui.base.StatusBarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MdWebView extends StatusBarActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_webview);
        ButterKnife.bind(this);
        onBtnOpenSourceUrlClick();

    }


    //@OnClick(R.id.md_WebView)
    protected void onBtnOpenSourceUrlClick() {
        //Navigator.openInBrowser(this, getString(R.string.open_source_url_content));
        //String url = "https://www.baidu.com/";
        //Intent intent = new Intent(this, MdWebView.class);
        Intent intent =this.getIntent();
        String url=intent.getStringExtra("url");
        webView = (WebView) findViewById(R.id.md_WebView);
        //启用支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //启用支持DOM Storage
        webView.getSettings().setDomStorageEnabled(true);
        //加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }


}
