package com.example.quiz.mvp2.wheel;


import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;

public interface WheelContract {

    interface View extends BaseView<Presenter> {
        void setRewardText(String Text);
        void onClickNextButton();
    }

    interface Presenter extends BasePresenter {
        void loadRewardText();
        void changeFragment();

    }
}
