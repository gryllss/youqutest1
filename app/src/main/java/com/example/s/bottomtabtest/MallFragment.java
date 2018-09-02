package com.example.s.bottomtabtest;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class MallFragment extends BackHandledFragment {



    public WebView mallWebView;

    private WebSettings webSettings;

    private String url = " http://www.youjiequ.com/index.php?r=index/wap";

    public MallFragment() {

    }

    public static MallFragment newInstance() {
        MallFragment fragment = new MallFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall, container, false);
        mallWebView = (WebView) view.findViewById(R.id.wv_mall);
        webSettings = mallWebView.getSettings();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mallWebView.getSettings().setJavaScriptEnabled(true);
        mallWebView.getSettings().setDomStorageEnabled(true);//部分网页可能加载不完全，需要打开DOM储存

        webSettings.setUseWideViewPort(true);//自适应屏幕大小
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mallWebView.loadUrl(url);
        mallWebView.setWebViewClient(new WebViewClient(){
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
        });


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
    public  boolean onBackPressed(){

        if(mallWebView.canGoBack()){
            mallWebView.goBack();
            Log.d("webView.goBack()", "webView.goBack()");
            return true;

        }else{
            Log.d("Conversatio退出","Conversatio退出");
            return false;
        }

    }







}