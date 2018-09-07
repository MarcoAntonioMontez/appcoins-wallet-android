package com.example.quiz.mvp2.menu;

import android.widget.TextView;

import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;
import com.example.quiz.mvp2.quiz.QuizContract;
import com.example.quiz.quiz.quizObjects.Question;

public interface MenuContract {
    interface View extends BaseView<Presenter> {
        void setRewardText(String str);

    }

    interface Presenter extends BasePresenter {
        void onLoad();

    }
}
