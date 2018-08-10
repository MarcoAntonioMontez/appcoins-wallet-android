package com.example.quiz.mvp;

public interface BaseMvpPresenter <V extends BaseView>{

    void attach(V view);

    void detach();

    boolean isAttached();
}
