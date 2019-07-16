package com.gdunn.owner.recipefeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_activity extends AppCompatActivity {
    public static String WEB_ADDRESS = "web_address";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = getIntent().getStringExtra(WEB_ADDRESS);

        if(url == null|| url.isEmpty()) finish();

        setContentView(R.layout.webview_activity);
        WebView webView = findViewById(R.id.test_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
