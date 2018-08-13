package com.example.quiz.mvp2.wheel;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.util.MathUtilsFunc;

public class WheelPresenter implements WheelContract.Presenter {

    private FragmentNavigator.Activity fragmentNavigator;
    private WheelContract.View wheelContractView;

    public WheelPresenter(WheelContract.View wheelContractView, FragmentNavigator.Activity fragmentNavigator){
        this.wheelContractView=wheelContractView;
        this.fragmentNavigator=fragmentNavigator;
        wheelContractView.setPresenter(this);
    }

    @Override
    public void changeFragment(){
        fragmentNavigator.setQuizFragment();

    }

    @Override
    public void loadRewardText() {
        String str = "Congratulations you won " + MathUtilsFunc.truncatedRandomDouble()+" Appc!";
        wheelContractView.setRewardText(str);
    }

    @Override
    public void start() {
    }
}
