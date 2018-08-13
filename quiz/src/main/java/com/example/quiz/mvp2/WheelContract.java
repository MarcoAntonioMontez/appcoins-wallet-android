package com.example.quiz.mvp2;


public interface WheelContract {

    interface View extends BaseView<Presenter>{
        void setRewardText(String Text);
    }

    interface Presenter extends BasePresenter{
        void loadRewardText();

    }
}
