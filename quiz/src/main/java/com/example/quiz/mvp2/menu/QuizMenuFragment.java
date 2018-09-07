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

public class QuizMenuFragment extends Fragment{

    private MainActivity myActivity;
    TextView playBtn;
    ImageView menuImg;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_quiz, container, false);

        myActivity= (MainActivity) getActivity();

        playBtn= (TextView) view.findViewById(R.id.play_btn_menu_quiz);
        menuImg = (ImageView) view.findViewById(R.id.menu_quiz_img);

        menuImg.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loadNextFragment();
                    }
                }
        );

        playBtn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loadNextFragment();
                    }
                }
        );

        return view;
    }

    public void loadNextFragment(){
        myActivity.setQuizFragment();
    }

}
