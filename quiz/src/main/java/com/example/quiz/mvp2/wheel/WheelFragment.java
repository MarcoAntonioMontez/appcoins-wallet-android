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

public class WheelFragment extends Fragment implements  WheelContract.View{
    LottieAnimationView animationView;
    ImageView buttonNext;
    ImageView appc_icon;
    WheelContract.Presenter mPresenter;
    MainActivity myActivity;
    RewardSaver rewardSaver;
    Context context;
    TextView rewardText;
    TextView timerText;
    TextView rewardWallet;

    long timeInMilliseconds= 1500; //milisec
    final long timeDivisions=50;
    long tickTime = (timeInMilliseconds/timeDivisions);

    double currentReward=0;
    double newReward;

    @Override
    public void setPresenter(WheelContract.Presenter presenter) {
        mPresenter=presenter;
    }

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.reward_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        buttonNext = (ImageView) view.findViewById(R.id.next_arrow_wheel);
        appc_icon=(ImageView) view.findViewById(R.id.appc_icon);
        animationView.setAnimation("wheel.json");
        timerText = (TextView) view.findViewById(R.id.wheel_timer_text);
        rewardWallet=(TextView) view.findViewById(R.id.reward_wallet);

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
    public void showCoinsNText() {

    }

    @Override
    public void fadeOut() {

    }

    @Override
    public void onClickNextButton() {
        mPresenter.changeFragment();
    }


//    @Override
//    public void setNextFragButtonVisibility(boolean visibility) {
//        if(visibility){
//            buttonNext.setVisibility(View.VISIBLE);
//        }  else{
//            buttonNext.setVisibility(View.INVISIBLE);
//        }
//    }


    @Override
    public void setRewardWalletVisibility(boolean visibility) {
        if(visibility){
            rewardWallet.setVisibility(View.VISIBLE);
        }  else{
            rewardWallet.setVisibility(View.INVISIBLE);
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
            appc_icon.setVisibility(View.VISIBLE);
        }  else{
            rewardText.setVisibility(View.INVISIBLE);
            appc_icon.setVisibility(View.INVISIBLE);
        }
    }


    public void showRewardAdder(final double reward, final TextView textview){
        this.newReward=reward;
        textview.setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(timeInMilliseconds, tickTime) {
            @Override
            public void onTick(long l) {
                currentReward=currentReward+(newReward/(double)timeDivisions);
                textview.setText("" + MathUtilsFunc.roundTwoDecimals(currentReward) );
            }

            @Override
            public void onFinish() {
                String endReward="" + MathUtilsFunc.roundTwoDecimals(reward);
                textview.setText(endReward);
                textview.setText(endReward);
            }
        }.start();
    }

    public TextView getTextView(String text){
        if(text.equals("rewardText")){
            return rewardText;
        } else if (text.equals("rewardWallet")){
            return rewardWallet;
        }else if (text.equals("timerText")){
            return rewardWallet;
        }
        return null;
    }


}
