package com.example.lmont.iceicebb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("VIDEO");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);
    }
}
