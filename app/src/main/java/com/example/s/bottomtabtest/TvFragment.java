package com.example.s.bottomtabtest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;

import ren.yale.android.cachewebviewlib.CacheType;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class TvFragment extends BackHandledFragment {


    public WebView tvWebView;

    private WebSettings webSettings;

    private String url = "http://wx.iptv789.com/?act=home";


    public TvFragment() {

    }

    public static TvFragment newInstance() {
        TvFragment fragment = new TvFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        tvWebView = (WebView) view.findViewById(R.id.wv_tv);
        webSettings = tvWebView.getSettings();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvWebView.getSettings().setJavaScriptEnabled(true);
        tvWebView.getSettings().setDomStorageEnabled(true);//部分网页可能加载不完全，需要打开DOM储存
        webSettings.setUseWideViewPort(true);//自适应屏幕大小
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        tvWebView.loadUrl(url);
        tvWebView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return WebViewCacheInterceptorInst.getInstance().interceptRequest(request);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return WebViewCacheInterceptorInst.getInstance().interceptRequest(url);

            }
        } );




        }

        private class MyWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //    return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                //     super.onReceivedError(view, errorCode, description, failingUrl);
                //  Toast.makeText(this,"网页加载错误！",0).show();
            }
        }

        @Override
        public boolean onBackPressed () {

            if (tvWebView.canGoBack()) {
                tvWebView.goBack();
                Log.d("webView.goBack()", "webView.goBack()");
                return true;

            } else {
                Log.d("Conversatio退出", "Conversatio退出");
                return false;
            }

        }


    }
