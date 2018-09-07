package com.example.quiz.mvp2.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;

public class WheelMenuDisabledFragment extends Fragment{

    private MainActivity myActivity;
    TextView playBtn;
    ImageView menuImg;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_wheel_disabled, container, false);

        myActivity= (MainActivity) getActivity();

        return view;
    }

    public void loadNextFragment(){
        //myActivity.setQuizFragment();
    }

}
