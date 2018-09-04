package com.example.quiz.mvp2.wheel;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.quiz.quizObjects.OnSwipeTouchListener;
import com.example.quiz.quiz.quizObjects.RewardSaver;
import com.example.quiz.util.MathUtilsFunc;

public class Dummy_fragment extends Fragment{

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v1_wheel_fragment, container, false);

        return view;
    }



}
