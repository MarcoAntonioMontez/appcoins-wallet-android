package com.example.quiz.mvp2.quiz;

import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;
import com.example.quiz.quiz.quizObjects.Question;

public interface QuizContract {
    interface View extends BaseView<Presenter> {
        public void onClickNextFragButton();
        public void onClickNextQuestionButton();
        public void updateQuestionTextAndOptions(Question question);
        public void updateQuestionText(String text);
        public void updateAnswerText(String text);
        public void setNextButtonVisibility(boolean visibility);
        public void setChangeFragButtonVisibility(boolean visibility);
        public void setAnswerVisibility(boolean visibility);
        public void setQuestionVisibility(boolean visibility);
        public void hideAll();
        public void initRadioGroup();
        public void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText);
        public void setEndScreenTextVisibility(boolean visibility);
    }

    interface Presenter extends BasePresenter {
        public void changeFragment();
        public Question loadNextQuestion();
        public void loadAnswerTextNButtons(String chosenOption);
    }
}
