package com.example.quiz.mvp;

import com.example.quiz.util.MathUtilsFunc;

import java.util.Random;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    @Override
    public void loadRewardText() {
        String str = "Congratulations you won " + MathUtilsFunc.truncatedRandomDouble()+" Appc!";
        getView().setRewardText(str);
    }
}
