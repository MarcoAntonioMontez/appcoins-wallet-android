package com.example.quiz.mvp2.wheel;

import android.support.v4.app.FragmentActivity;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.quiz.quizObjects.RewardSaver;
import com.example.quiz.util.MathUtilsFunc;

public class WheelPresenter implements WheelContract.Presenter {

    private FragmentNavigator.Activity fragmentNavigator;
    private WheelContract.View wheelContractView;
    RewardSaver rewardSaver;
    private boolean firstWheelRun = true;

    public WheelPresenter(WheelContract.View wheelContractView, FragmentNavigator.Activity fragmentNavigator,RewardSaver rewardSaver){
        this.wheelContractView=wheelContractView;
        this.fragmentNavigator=fragmentNavigator;
        this.rewardSaver=rewardSaver;
        wheelContractView.setPresenter(this);
    }

    @Override
    public void changeFragment(){
        fragmentNavigator.setQuizFragment();

    }

    @Override
    public void runWheel() {
        if(firstWheelRun){
            wheelContractView.setRewardTextVisibility(false);
            wheelContractView.animateWheel();
            firstWheelRun=false;
        }
    }

    @Override
    public void loadRewardText() {
        double reward=MathUtilsFunc.truncatedRandomDouble();
        rewardSaver.setReward(reward);
        String str = "Congratulations you won " + reward +" Appc!";
        wheelContractView.setRewardText(str);
        wheelContractView.setRewardTextVisibility(true);
        wheelContractView.setNextFragButtonVisibility(true);

        wheelContractView.showRewardAdder(reward);
    }

    @Override
    public void start() {
    }

}
