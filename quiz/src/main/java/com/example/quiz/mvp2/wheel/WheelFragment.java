package com.example.quiz.mvp2.wheel;

import android.animation.Animator;
import android.os.Bundle;
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
import com.example.quiz.quiz.quizObjects.RewardSaver;

public class WheelFragment extends Fragment implements  WheelContract.View{
    LottieAnimationView animationView;
    ImageView buttonNext;
    TextView rewardText;
    WheelContract.Presenter mPresenter;
    MainActivity myActivity;
    RewardSaver rewardSaver;

    @Override
    public void setPresenter(WheelContract.Presenter presenter) {
        mPresenter=presenter;
    }

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.reward_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        buttonNext = (ImageView) view.findViewById(R.id.next_arrow_wheel);
        animationView.setAnimation("wheel.json");

        myActivity= (MainActivity) getActivity();
        rewardSaver=myActivity.getRewardSaver();


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
//        buttonWheel.setOnClickListener( new View.OnClickListener(){
//            public void onClick(View v){
//                runWheelAnimation(animationView);
//            }
//        });

        animationView.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                onClickWheelButton();
            }
        });



        buttonNext.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        onClickNextButton();
                    }
                }
        );
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

    @Override
    public void onClickNextButton() {
        mPresenter.changeFragment();
    }


    @Override
    public void setNextFragButtonVisibility(boolean visibility) {
        if(visibility){
            buttonNext.setVisibility(View.VISIBLE);
        }  else{
            buttonNext.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClickWheelButton() {
        mPresenter.runWheel();
    }

    @Override
    public void animateWheel() {
        runWheelAnimation(animationView);
    }

    @Override
    public void setRewardTextVisibility(boolean visibility) {
        if(visibility){
            rewardText.setVisibility(View.VISIBLE);
        }  else{
            rewardText.setVisibility(View.INVISIBLE);
        }
    }


}
