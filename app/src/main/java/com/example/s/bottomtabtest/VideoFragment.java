package com.example.s.bottomtabtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.s.bottomtabtest.VideoFragment_LoopView.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private LoopViewPager viewPager;
    private List<String> images = new ArrayList<>();

    private long exittime = 0;

    public VideoFragment() {

    }

    public static Fragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        viewPager = (LoopViewPager)view.findViewById(R.id.viewpager);
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504330680909&di=a615ac65f3e2084626984f110bd918ef&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f6d057a988cf0000018c1b29283d.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504332690541&di=c47a1a1737653e07e288e42b977c63c0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F016f705553f4090000009c5091c0b7.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504332936143&di=33523d984b211a81558577f0f7795377&imgtype=0&src=http%3A%2F%2Feasyread.ph.126.net%2FRjNwlT5__FJHVtVaassowQ%3D%3D%2F6597180817330744819.jpg");
        viewPager.setData(images);

        return view;
    }


}



