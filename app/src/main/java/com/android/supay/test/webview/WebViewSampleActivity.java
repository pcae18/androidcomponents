package com.android.supay.test.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.android.supay.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewSampleActivity extends AppCompatActivity {

    @BindView(R.id.webView) WebView webView;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_sample);
        ButterKnife.bind(this);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl("https://siiamco.udg.mx/cas/login?service=https://siiamco.udg.mx/usuarios/shiro-cas");
    }


}
