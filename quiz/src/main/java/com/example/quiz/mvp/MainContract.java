package com.example.quiz.mvp;

public interface MainContract {

    interface Presenter extends BaseMvpPresenter<MainContract.View>{
        void loadRewardText();

    }

    interface View extends BaseView {
        void setRewardText(String Text);
    }
}
