package com.example.lmont.iceicebb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("VIDEO");
        final String name = getIntent().getStringExtra("NAME");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);

        Button backToGame = (Button)findViewById(R.id.leaveWebView);
        backToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
