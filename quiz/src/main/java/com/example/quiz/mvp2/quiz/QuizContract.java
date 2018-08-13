package com.example.quiz.mvp2.quiz;

import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;
import com.example.quiz.quiz.quizObjects.Question;

public interface QuizContract {
    interface View extends BaseView<Presenter> {
        public void onClickNextButton();
        public void updateQuestionText(Question question);
    }

    interface Presenter extends BasePresenter {
        public void changeFragment();
        public void loadNextQuestion();
        public boolean hasQuestions();
    }
}
