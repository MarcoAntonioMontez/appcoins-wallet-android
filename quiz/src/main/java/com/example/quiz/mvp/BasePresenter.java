package com.example.quiz.mvp;

public class BasePresenter <V extends BaseView> implements BaseMvpPresenter<V>{

    private V mView;

    @Override
    public void attach(V view) {
        mView=view;
    }

    @Override
    public void detach() {
        mView=null;
    }

    @Override
    public boolean isAttached() {
        return mView != null;
    }

    public V getView(){
        return mView;
    }
}
