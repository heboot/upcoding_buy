package com.upcoding.buy.utils;

import android.databinding.BindingAdapter;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * Created by Heboot on 2017/5/3.
 */

public class WebUtils {
    /**
     * 首页推荐列表的图片
     */
    @BindingAdapter("android:loadUrl")
    public static void loadUrl(WebView webView, String url) {
        initWebView(webView);
        loadWebView(webView);
        webView.loadUrl(url);
    }

    private static void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setDisplayZoomControls(false);
        // 缩放按钮
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);

    }

    private static void loadWebView(WebView webView) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) {
                    webView.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
}
