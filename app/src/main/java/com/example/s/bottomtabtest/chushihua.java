package com.example.s.bottomtabtest;

import android.app.Application;

import java.io.File;

import ren.yale.android.cachewebviewlib.CacheType;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class chushihua extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WebViewCacheInterceptorInst.getInstance().
                init(new WebViewCacheInterceptor.Builder(this));
        WebViewCacheInterceptor.Builder builder =  new WebViewCacheInterceptor.Builder(this);

        builder.setCachePath(new File(this.getCacheDir(),"cache_path_name"))//设置缓存路径，默认getCacheDir，名称CacheWebViewCache
                .setCacheSize(1024*1024*100)//设置缓存大小，默认100M
                .setConnectTimeoutSecond(20)//设置http请求链接超时，默认20秒
                .setReadTimeoutSecond(20)//设置http请求链接读取超时，默认20秒
                .setCacheType(CacheType.NORMAL);//设置缓存为正常模式，默认模式为强制缓存静态资源

        WebViewCacheInterceptorInst.getInstance().init(builder);

    }
}
