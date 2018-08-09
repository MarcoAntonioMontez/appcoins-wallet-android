package com.example.quiz.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;

public class LobbyFragment extends Fragment{

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizv2, container, false);

        textView = (TextView) view.findViewById(R.id.lobby_text);

        return view;
    }

    public void updateText(String text) {
        textView.setText(text);
    }

}
