package com.example.quiz.mvp2;

import com.example.quiz.util.MathUtilsFunc;

public class WheelPresenter implements WheelContract.Presenter {


    private WheelContract.View wheelContractView;

    public WheelPresenter(WheelContract.View wheelContractView){
        this.wheelContractView=wheelContractView;
        wheelContractView.setPresenter(this);
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
