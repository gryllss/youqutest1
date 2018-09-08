package com.example.s.bottomtabtest;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.s.bottomtabtest.VideoFragment_LoopView.LoopViewPager;
import com.tencent.smtt.sdk.VideoActivity;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment  {
    ImageView image_aiyiqi;

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
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        viewPager = (LoopViewPager) view.findViewById(R.id.viewpager);

        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcugl7ij20hs0bv0t5.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcuob7jj20k008cae7.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcuvuzsj20k008cn1q.jpg");
        images.add("https://wx1.sinaimg.cn/mw690/b0653590gy1fv0zcv2qpmj20u00itwkt.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcvgxw4j20jt078q5c.jpg");
        viewPager.setData(images);

        image_aiyiqi = (ImageView)view.findViewById(R.id.imageview_aiqiyi);
        ImageView image_tengxun = (ImageView)view.findViewById(R.id.imageview_tengxun);
        ImageView image_souhu = (ImageView)view.findViewById(R.id.imageview_souhu);
        ImageView image_youku = (ImageView)view.findViewById(R.id.imageview_youku);
        ImageView image_mangguo = (ImageView)view.findViewById(R.id.imageview_mangguo);
        ImageView image_tudou = (ImageView)view.findViewById(R.id.imageview_tudou);
        ImageView image_m1905 = (ImageView)view.findViewById(R.id.imageview_m1905);
        ImageView image_leshi = (ImageView)view.findViewById(R.id.imageview_leshi);
        ImageView image_ppshipin = (ImageView)view.findViewById(R.id.imageview_ppshipin);
        ImageView image_lishipin = (ImageView)view.findViewById(R.id.imageview_lishipin);
        ImageView image_dongman = (ImageView)view.findViewById(R.id.imageview_dongman);
        ImageView image_dianshi = (ImageView)view.findViewById(R.id.imageview_dianshi);
        image_aiyiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BrowserActivity.class);
                getActivity().startActivity(intent);

            }
        });



        return view;
    }




}



