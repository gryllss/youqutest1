package com.example.s.bottomtabtest;

import android.app.Fragment;
import android.app.FragmentTransaction;
//import android.support.v4.app.FragmentActivity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class MainActivity extends FragmentActivity implements BackHandledInterface {

    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;

    private RadioGroup mRgTab;
    private List<Fragment> mFragmentList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRgTab = (RadioGroup) findViewById(R.id.rg_main);
        mRgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_video:
                        changeFragment(VideoFragment.class.getName());
                        break;
                    case R.id.rb_tv:
                        changeFragment(TvFragment.class.getName());
                        break;
                    case R.id.rb_mall:
                        changeFragment(MallFragment.class.getName());
                        break;
                    case R.id.rb_coupon:
                        changeFragment(CouponFragment.class.getName());
                        break;
                    case R.id.rb_my:
                        changeFragment(MyFragment.class.getName());

//                        CleanMessageUtil.clearAllCache(getApplicationContext());//这个是可以用于清楚缓存的
                        break;
                }
            }
        });
        if (savedInstanceState == null) {
            changeFragment(VideoFragment.class.getName());
        }




    }

    public void changeFragment(String tag) {
        hideFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            if (tag.equals(VideoFragment.class.getName())) {
                fragment = VideoFragment.newInstance();

            } else if (tag.equals(TvFragment.class.getName())) {
                fragment = TvFragment.newInstance();
            } else if (tag.equals(MallFragment.class.getName())) {
                fragment = MallFragment.newInstance();
            } else if (tag.equals(CouponFragment.class.getName())) {
                fragment = CouponFragment.newInstance();
            } else if (tag.equals(MyFragment.class.getName())) {
                fragment = MyFragment.newInstance();
            }
            mFragmentList.add(fragment);
            transaction.add(R.id.fl_container, fragment, fragment.getClass().getName());
        }
        transaction.commitAllowingStateLoss();

    }


    private void hideFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (Fragment f : mFragmentList) {
            ft.hide(f);
        }
        ft.commit();
    }




    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){

                super.onBackPressed(); //退出
            }else{
                getSupportFragmentManager().popBackStack(); //fragment 出栈
            }
        }
    }







}
