package com.example.quiz.quiz;

import android.view.*;

public class QuizPresenter implements QuizContract.QuizPresenter {
    protected View view;

    QuizPresenter(View view){
        this.view=view;
    }

    @Override
    public void loadCommonGreeting() {

    }

    @Override
    public void loadLobbyGreeting() {

    }
}
