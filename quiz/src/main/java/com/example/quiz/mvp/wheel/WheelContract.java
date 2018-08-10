package com.example.quiz.mvp.wheel;

import com.example.quiz.mvp.BaseMvpPresenter;
import com.example.quiz.mvp.BaseView;
import com.example.quiz.mvp.MainContract;

public interface WheelContract {

    interface Presenter extends BaseMvpPresenter<MainContract.View> {
        void loadRewardText();
        void onChangeButtonClick();
        void onSpinButtonClick();

    }

    interface View extends BaseView {
        void setRewardText(String Text);

    }

}
