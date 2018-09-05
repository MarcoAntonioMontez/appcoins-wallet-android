package com.example.quiz.mvp2.quiz;

import android.widget.RadioButton;
import android.widget.RelativeLayout;

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
        public void updateAnswerColor(int textColor);
        public void setQuestionVisibility(boolean visibility);
        public void hideAll();
        public void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText);
        public void setEndScreenTextVisibility(boolean visibility);
        public void changeTimerText(String text);
        public void setTimerTextColor(int color);
        public String getButtonText(RelativeLayout button);

        public RelativeLayout getButtonFromText(String text);
        public void updateWrongAnswerColors(RelativeLayout wrongButton, RelativeLayout rightButton);
        public void resetOptionColors();
        public void updateRightAnswerColors(RelativeLayout rightButton);
    }

    interface Presenter extends BasePresenter {
        public void changeFragment();
        public Question loadNextQuestion();
        public void loadAnswer(RelativeLayout relativeLayout);
        public void startTimer();
        public boolean isQuestionAnswered();
    }
}
