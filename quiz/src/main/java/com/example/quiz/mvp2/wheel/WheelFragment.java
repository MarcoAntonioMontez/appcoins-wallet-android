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

public class WheelFragment extends Fragment implements  WheelContract.View{
    LottieAnimationView animationView;
    ImageView buttonNext;
    TextView rewardText;
    WheelContract.Presenter mPresenter;
    MainActivity myActivity;
    RewardSaver rewardSaver;
    Context context;

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
        context=view.getContext();

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

        animationView.setOnClickListener(
                new View.OnClickListener(){
                public void onClick(View v){
                    onClickWheelButton();
                }
            }
        );

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

//    public void showRewardAdder(double reward){
//        long timeInMilliseconds= 2000; //2sec
//        long timeDivisions=50;
//        long tickTime = (timeInMilliseconds/timeDivisions);
//
//        CountDownTimer countDownTimer = new CountDownTimer(timeInMilliseconds, tickTime) {
//            @Override
//            public void onTick(long l) {
//                updateTimerColor((int) l / 1000);
//                quizContractView.changeTimerText("" + (int) l / 1000);
//            }
//
//            @Override
//            public void onFinish() {
//                timerRunning = false;
//                if (!quizContractView.isQuestionAnswered()) {
//                    quizContractView.setAnswerVisibility(true);
//                    quizContractView.setNextButtonVisibility(true);
//                    quizContractView.updateAnswerText("Time has run out :(");
//                    quizContractView.updateAnswerColor(Color.RED);
//                    quizContractView.setConfirmButtonVisibility(false);
//                } else {
//                    quizContractView.changeTimerText("End");
//                }
//            }
//        }.start();
//
//    }


}
