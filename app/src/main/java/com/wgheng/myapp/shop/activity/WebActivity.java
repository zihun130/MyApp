package com.wgheng.myapp.shop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wgheng.myapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);


        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        tvTitle.setText(title);
        ivBack.setVisibility(View.VISIBLE);

        initWebView(url);

    }

    private void initWebView(String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (progressBar.getProgress() >= 100) {
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (TextUtils.isEmpty(tvTitle.getText().toString())) {
                    tvTitle.setText(title);
                }
            }
        });
        webView.loadUrl(url);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
