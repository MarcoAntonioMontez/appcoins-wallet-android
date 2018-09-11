package com.example.quiz.mvp2.quiz;

import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quiz.mvp2.BasePresenter;
import com.example.quiz.mvp2.BaseView;
import com.example.quiz.quiz.quizObjects.Question;

import org.w3c.dom.Text;

public interface QuizContract {
    interface View extends BaseView<Presenter> {
        void onClickNextFragButton();
        void onClickNextQuestionButton();
        void updateQuestionTextAndOptions(Question question);
        void updateQuestionText(String text);
        void hideAll();
        void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText);
        void setEndScreenTextVisibility(boolean visibility);
        String getButtonText(TextView button);

        TextView getButtonFromText(String text);
        void updateWrongAnswerColors(TextView wrongButton, TextView rightButton);
        void resetOptionColors();
        void updateRightAnswerColors(TextView rightButton);
        void setRewardText(String text);
        public void showRewardAdder(final double reward, final TextView textview);
        public TextView getTextView(String text);
        public void resetButtonsColor();
        void setTimerOutScreenVisibility(boolean visibility);
        boolean isTimerOutOnScreen();
        void setQuizButtonsClickable(boolean isClickable);
        void runTimerAnimation();
        public void pauseTimerAnimation();
        public void colorQuestionIndex(int questionId);
        void runCoinsAnimation();
    }

    interface Presenter extends BasePresenter {
        void changeFragment();
        Question loadNextQuestion();
        void loadAnswer(TextView TextView);
        boolean isQuestionAnswered();
        void loadQuestionsFromTimerButton();
        void onTimerEnd();
    }
}
