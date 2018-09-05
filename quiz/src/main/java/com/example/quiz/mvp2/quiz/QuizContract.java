package com.example.quiz.mvp2.quiz;

import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;
import com.example.quiz.quiz.quizObjects.Question;

public interface QuizContract {
    interface View extends BaseView<Presenter> {
        void onClickNextFragButton();
        void onClickNextQuestionButton();
        void updateQuestionTextAndOptions(Question question);
        void updateQuestionText(String text);
        void hideAll();
        void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText);
        void setEndScreenTextVisibility(boolean visibility);
        void changeTimerText(String text);
        void setTimerTextColor(int color);
        String getButtonText(RelativeLayout button);

        RelativeLayout getButtonFromText(String text);
        void updateWrongAnswerColors(RelativeLayout wrongButton, RelativeLayout rightButton);
        void resetOptionColors();
        void updateRightAnswerColors(RelativeLayout rightButton);
        void setRewardText(String text);
        public void showRewardAdder(final double reward, final TextView textview);
        public TextView getTextView(String text);
        public void resetButtonsColor();
    }

    interface Presenter extends BasePresenter {
        void changeFragment();
        Question loadNextQuestion();
        void loadAnswer(RelativeLayout relativeLayout);
        void startTimer();
        boolean isQuestionAnswered();
    }
}
