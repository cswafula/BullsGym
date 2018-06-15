package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Music extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        WebView mywebView= findViewById(R.id.workout);
        mywebView.loadUrl("https://www.youtube.com/results?search_query=workout+music");
        WebSettings webSettings= mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mywebView.setWebViewClient(new WebViewClient());


    }
}
