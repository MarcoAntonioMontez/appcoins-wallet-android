package com.example.quiz.quiz;

public interface QuizContract {

    interface QuizView {

        void onCommonGreetingButtonClicked();
        void onLobbyGreetingButtonClicked();

        void displayGreeting(String greeting);

    }

    interface QuizPresenter {

        void loadCommonGreeting();
        void loadLobbyGreeting();

    }
}
