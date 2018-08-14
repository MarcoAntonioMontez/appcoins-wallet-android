package com.example.quiz.mvp2.wheel;

import android.support.v4.app.FragmentActivity;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.util.MathUtilsFunc;

public class WheelPresenter implements WheelContract.Presenter {

    private FragmentNavigator.Activity fragmentNavigator;
    private WheelContract.View wheelContractView;
    private MainActivity myActivity;

    public WheelPresenter(WheelContract.View wheelContractView, FragmentNavigator.Activity fragmentNavigator, MainActivity myActivity){
        this.wheelContractView=wheelContractView;
        this.fragmentNavigator=fragmentNavigator;
        wheelContractView.setPresenter(this);
    }

    @Override
    public void changeFragment(){
        fragmentNavigator.setQuizFragment();

    }

    @Override
    public void saveRewardOnActivity( double rewardValue) {
        myActivity.saveRewardValue(rewardValue);
    }

    @Override
    public void loadRewardText() {
        double reward=MathUtilsFunc.truncatedRandomDouble();
        String str = "Congratulations you won " + reward +" Appc!";
        wheelContractView.setRewardText(str);
        saveRewardOnActivity(reward);
    }

    @Override
    public void start() {
    }
}
