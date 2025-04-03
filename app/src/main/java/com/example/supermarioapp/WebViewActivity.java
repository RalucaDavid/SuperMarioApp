package com.example.supermarioapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    private WebView characterWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        characterWebView = findViewById(R.id.characterWebView);
        characterWebView.getSettings().setJavaScriptEnabled(true);
        characterWebView.setWebViewClient(new WebViewClient());

        String characterUrl = getIntent().getStringExtra("CHARACTER_URL");

        if (characterUrl != null) {
            characterWebView.loadUrl(characterUrl);
        }
    }

    @Override
    public void onBackPressed() {
        if (characterWebView.canGoBack()) {
            characterWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
