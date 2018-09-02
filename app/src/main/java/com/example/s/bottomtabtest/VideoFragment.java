package com.example.s.bottomtabtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class VideoFragment extends BackHandledFragment {

    private long exittime = 0;
    public VideoFragment() {

    }

    public static VideoFragment newInstance() {
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
        return inflater.inflate(R.layout.fragment_video,container,false);
    }


    @Override
    public boolean onBackPressed() {
        if (System.currentTimeMillis() - exittime < 2000) {
//                    Log.i("tag", "onKeyDown: " + "退出程序");
            getActivity().finish();
            //System.exit(0);
        } else {
            Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exittime = System.currentTimeMillis();
        }
//                Log.d("Conversatio退出", "Conversatio退出");
        return true;
    }


    }



