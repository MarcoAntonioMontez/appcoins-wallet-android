package com.example.quiz.mvp2.loading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz.R;
import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.MainActivity;

public class LoadingFragment extends Fragment  {

    private MainActivity myActivity;
    final Handler handler= new Handler();
    int time=2000;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_fragment, container, false);

        myActivity= (MainActivity) getActivity();

        loadNextFragmentAfterTime(time);
        return view;
    }

    public void loadNextFragmentAfterTime(int time){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myActivity.setMenuWheelFragment();
            }
        }, time);
    }

}