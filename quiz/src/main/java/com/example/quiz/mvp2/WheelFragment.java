package com.example.quiz.mvp2;

import android.animation.Animator;
import android.app.Activity;
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
import com.example.quiz.util.MathUtilsFunc;

public class WheelFragment extends Fragment implements  WheelContract.View{
    LottieAnimationView animationView;
    Button buttonWheel;
    Button buttonNext;
    TextView rewardText;
    WheelContract.Presenter mPresenter;

    @Override
    public void setPresenter(WheelContract.Presenter presenter) {
        mPresenter=presenter;
    }

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.reward_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        buttonWheel = (Button) view.findViewById(R.id.button_wheel);
        buttonNext = (Button) view.findViewById(R.id.button_next);
        animationView.setAnimation("wheel.json");

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mPresenter.loadRewardText();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        //Run Lottie Animation On Click
        buttonWheel.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                runWheelAnimation(animationView);
            }
        });

//        buttonNext.setOnClickListener(
//                new View.OnClickListener(){
//                    public void onClick(View v){
//                        activityCommander.gotoQuizFrag();
//                    }
//                }
//        );
        return view;
    }

    public void runWheelAnimation(LottieAnimationView animationView){
        animationView.setRepeatCount(1);
        animationView.playAnimation();
    }

    @Override
    public void setRewardText(String text) {
        rewardText.setText(text);
    }


}
