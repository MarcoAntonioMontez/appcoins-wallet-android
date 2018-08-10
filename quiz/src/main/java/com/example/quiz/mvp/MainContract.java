package com.example.quiz.mvp;

public interface MainContract {

    interface Presenter extends BaseMvpPresenter<MainContract.View>{
        void loadHelloText();

    }

    interface View extends BaseView {
        void onTextLoaded(String Text);
    }
}
