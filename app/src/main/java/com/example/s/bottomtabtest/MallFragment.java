package com.example.s.bottomtabtest;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class MallFragment extends Fragment {


    public WebView mallWebView;

    private WebSettings webSettings;

    private String url = " http://www.youjiequ.com/index.php?r=index/wap";

    private long exittime = 0;


    public MallFragment() {

    }

    public static Fragment newInstance() {
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
        mallWebView.getSettings().setJavaScriptEnabled(true);

        mallWebView.getSettings().setDomStorageEnabled(true);//部分网页可能加载不完全，需要打开DOM储存

        webSettings.setUseWideViewPort(true);//自适应屏幕大小
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        TextView tvRefreshMall = (TextView) view.findViewById(R.id.mall_refresh);
        tvRefreshMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mallWebView.reload();

            }
        });

        mallWebView.loadUrl(url);
        mallWebView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return  WebViewCacheInterceptorInst.getInstance().interceptRequest(request);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return  WebViewCacheInterceptorInst.getInstance().interceptRequest(url);
            }
        });

        mallWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK ) {
                        //这里处理返回键事件
                        if (mallWebView.canGoBack()){
                            mallWebView.goBack();
//                            Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                            return true;
                        }else {
                            if (System.currentTimeMillis() - exittime <2000){
                                getActivity().finish();
                            }else {
                                exittime = System.currentTimeMillis();
                                Toast.makeText(getActivity(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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




}
