package com.example.s.bottomtabtest;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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

public class CouponFragment extends BackHandledFragment {

    public WebView couponWebView;

//    private WebSettings webSettings;

    private String url = "http://www.youjiequ.com/index.php?r=class/sub";

    private long exittime = 0;

    public CouponFragment() {

    }

    public static CouponFragment newInstance() {
        CouponFragment fragment = new CouponFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        couponWebView = (WebView) view.findViewById(R.id.wv_coupon);
//        webSettings = couponWebView.getSettings();
        final TextView tvRefreshcoupon = (TextView) view.findViewById(R.id.coupon_refresh);
        tvRefreshcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponWebView.reload();
            }
        });

        couponWebView.loadUrl(url);
        couponWebView.setWebViewClient(new WebViewClient(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                WebViewCacheInterceptorInst.getInstance().loadUrl(couponWebView, request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebViewCacheInterceptorInst.getInstance().loadUrl(couponWebView, url);
                return true;
            }

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        couponWebView.getSettings().setJavaScriptEnabled(true);
//        webSettings.setUseWideViewPort(true);//自适应屏幕大小
//        webSettings.setLoadWithOverviewMode(true);
//        couponWebView.getSettings().setDomStorageEnabled(true);//部分网页可能加载不完全，需要打开DOM储存
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);



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

        if(couponWebView.canGoBack()){
            couponWebView.goBack();
            Log.d("webView.goBack()", "webView.goBack()");
            return true;

        }else{
            //自己加的
            if (System.currentTimeMillis() - exittime < 2000) {
//                    Log.i("tag", "onKeyDown: " + "退出程序");
                getActivity().finish();
                //System.exit(0);
            } else {
                Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exittime = System.currentTimeMillis();
                return true;
            }
//                Log.d("Conversatio退出", "Conversatio退出");
            return false;
        }

    }
}
