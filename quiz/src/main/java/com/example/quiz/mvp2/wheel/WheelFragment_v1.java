package com.example.quiz.mvp2.wheel;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class WheelFragment_v1 extends Fragment implements  WheelContract.View{
    WheelContract.Presenter mPresenter;
    MainActivity myActivity;
    RewardSaver rewardSaver;
    Context context;
    LottieAnimationView animationView;
    LottieAnimationView coinsAnimationView;
    Button playBtn;
    ImageView prevBtn;
    TextView rewardText;
    TextView timerText;
    TextView rewardWallet;

    ImageView greyBackground;
    ImageView coins;
    TextView coinsText;
    Animation animationFade;
    private static final String TAG = "MyActivity";
    int debugIndex=0;




    //Reward Adder
    long timeInMilliseconds= 1000; //milisec
    final long timeDivisions=50;
    long tickTime = (timeInMilliseconds/timeDivisions);

    double currentReward=0;
    double newReward=0;

    @Override
    public void setPresenter(WheelContract.Presenter presenter) {
        mPresenter=presenter;
    }

    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.v1_wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.cur_prize_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        coinsAnimationView = (LottieAnimationView) view.findViewById(R.id.coins_animation);
        prevBtn = (ImageView) view.findViewById(R.id.left_arrow);
        animationView.setAnimation("final_wheel.json");
        coinsAnimationView.setAnimation("Coin-animation-v2.json");
        timerText = (TextView) view.findViewById(R.id.wheel_timer_text);
        rewardWallet=(TextView) view.findViewById(R.id.reward_wallet);
        playBtn = (Button) view.findViewById(R.id.play_btn);
        greyBackground = (ImageView) view.findViewById(R.id.gray_background);
        coinsText = (TextView) view.findViewById(R.id.coins_text);

        myActivity= (MainActivity) getActivity();
        rewardSaver=myActivity.getRewardSaver();
        context=view.getContext();

        animationFade = (Animation) AnimationUtils.loadAnimation(context,R.anim.fade_in);

        animationFade.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mPresenter.loadRewardText();
                greyBackground.setOnClickListener(
                        new View.OnClickListener(){
                            public void onClick(View v){
                                onClickNextButton();
                            }
                        }
                );
            }
        });

        coinsAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                CountDownTimer countDownTimer1 = new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        mPresenter.changeFragment();
                    }
                }.start();


            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });


        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fadeOut();

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

        playBtn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        onClickWheelButton();
                    }
                }
        );

        prevBtn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        myActivity.finish();
                    }
                }
        );
        return view;
    }


    public void runWheelAnimation(LottieAnimationView animationView){
        animationView.playAnimation();
    }

    @Override
    public void runCoinsAnimation(){
        coinsAnimationView.playAnimation();
    }


    @Override
    public void setRewardText(String text) {
        rewardText.setText(text);
    }

    @Override
    public void showCoinsNText() {
        greyBackground.setVisibility(View.VISIBLE);
        coinsAnimationView.setVisibility(View.VISIBLE);
        coinsText.setVisibility(View.VISIBLE);
    }


    @Override
    public void fadeOut() {
        greyBackground.startAnimation(animationFade);
    }

    @Override
    public void onClickNextButton() {
        mPresenter.changeFragment();
    }


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
        }  else{
            rewardText.setVisibility(View.INVISIBLE);
        }
    }


    public void showRewardAdder(final double reward, final TextView textview){
        this.newReward=reward;

        CountDownTimer countDownTimer = new CountDownTimer(timeInMilliseconds, tickTime) {
            @Override
            public void onTick(long l) {
                String str="" + MathUtilsFunc.roundTwoDecimals(currentReward) + " APPC";
                currentReward=currentReward+((double)newReward/(double)timeDivisions);
                textview.setText(str);
            }

            @Override
            public void onFinish() {
                String endReward="" + MathUtilsFunc.roundTwoDecimals(reward) + " APPC";
                textview.setText(endReward);
            }
        }.start();
    }

    public TextView getTextView(String text){
        if(text.equals("rewardText")){
            return rewardText;
        } else if (text.equals("coinsText")){
            return coinsText;
        }else if (text.equals("timerText")){
            return rewardWallet;
        }
        return null;
    }


}
