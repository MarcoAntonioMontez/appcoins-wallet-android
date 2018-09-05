package com.example.quiz.mvp2.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.quiz.quizObjects.Question;
import com.example.quiz.quiz.quizObjects.RewardSaver;

import org.w3c.dom.Text;

import java.util.LinkedList;


public class QuizFragment extends Fragment implements QuizContract.View{
    LottieAnimationView animationView;
    ImageView nextQuestionButton;
    TextView question;
    TextView text_result;
    TextView wheelScoreText;
    TextView quizScoreText;
    TextView totalScoreText;
    private TextView countDownText;


    Question currQuestion;
    LinkedList<Question> questionList;
    private final String TAG = "QuizFragment";
    private QuizContract.Presenter mPresenter;
    private MainActivity myActivity;
    private RewardSaver rewardSaver;
    private RelativeLayout quizBtn1;
    private RelativeLayout quizBtn2;
    private RelativeLayout quizBtn3;
    private RelativeLayout quizBtn4;
    private TextView quizBtnText1;
    private TextView quizBtnText2;
    private TextView quizBtnText3;
    private TextView quizBtnText4;


    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        mPresenter=presenter;
    }

        @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        question = (TextView) view.findViewById(R.id.question_text_quiz);
        totalScoreText = (TextView) view.findViewById(R.id.totalReward);
        countDownText = (TextView) view.findViewById(R.id.timerText);
        quizBtn1 = (RelativeLayout) view.findViewById(R.id.quiz_btn_1);
        quizBtn2 = (RelativeLayout) view.findViewById(R.id.quiz_btn_2);
        quizBtn3 = (RelativeLayout) view.findViewById(R.id.quiz_btn_3);
        quizBtn4 = (RelativeLayout) view.findViewById(R.id.quiz_btn_4);

        quizBtnText1 = (TextView) view.findViewById(R.id.quiz_btn_text_1);
        quizBtnText2 = (TextView) view.findViewById(R.id.quiz_btn_text_2);
        quizBtnText3 = (TextView) view.findViewById(R.id.quiz_btn_text_3);
        quizBtnText4 = (TextView) view.findViewById(R.id.quiz_btn_text_4);

        myActivity= (MainActivity) getActivity();
        rewardSaver=myActivity.getRewardSaver();


        mPresenter.loadNextQuestion();

        quizBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtn1);
            }
        });

        quizBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtn2);

            }
        });

        quizBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtn3);

            }
        });

        quizBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtn4);

            }
        });

        return view;
    }

    @Override
    public String getButtonText(RelativeLayout button){
        TextView textView=null;
        String text="Button not Found";
        if(button==quizBtn1){
             textView=quizBtnText1;
        } else if(button==quizBtn2) {
            textView=quizBtnText2;
        } else if(button==quizBtn3) {
            textView=quizBtnText3;
        } else if(button==quizBtn4) {
            textView=quizBtnText4;
        }
        if(textView!=null){
            text=textView.getText().toString();
        }

        return text;
    }

    public void hideAll() {
        nextQuestionButton.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        quizBtn1.setVisibility(View.INVISIBLE);
        quizBtn2.setVisibility(View.INVISIBLE);
        quizBtn3.setVisibility(View.INVISIBLE);
        quizBtn4.setVisibility(View.INVISIBLE);
        text_result.setVisibility(View.INVISIBLE);

    }
    public void updateText(TextView line, String str){
        line.setText(str);
    }

    public void updateQuestionTextAndOptions(Question newQuestion){
        LinkedList<String> optionsList = newQuestion.getOptionsList();
        question.setText(newQuestion.getQuestion());

        quizBtnText1.setText(optionsList.get(0));
        quizBtnText2.setText(optionsList.get(1));
        quizBtnText3.setText(optionsList.get(2));
        quizBtnText4.setText(optionsList.get(3));
    }

    @Override
    public void updateAnswerText(String text) {

        updateText(text_result, text);
        text_result.setTextColor(Color.GREEN);
    }

    public void updateAnswerColor(int textColor){
        text_result.setTextColor(textColor);
    }


    @Override
    public void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText) {
       this.wheelScoreText.setText(wheelScoreText);
       this.quizScoreText.setText(quizScoreText);
       this.totalScoreText.setText(totalScoreText);
    }

    public void setEndScreenTextVisibility(boolean visibility){
        if(visibility){
            wheelScoreText.setVisibility(View.VISIBLE);
            quizScoreText.setVisibility(View.VISIBLE);
            totalScoreText.setVisibility(View.VISIBLE);
        }else{
            wheelScoreText.setVisibility(View.INVISIBLE);
            quizScoreText.setVisibility(View.INVISIBLE);
            totalScoreText.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void changeTimerText(String text) {
        countDownText.setText(text);
    }

    @Override
    public void setTimerTextColor(int color) {
        countDownText.setTextColor(color);
    }

    @Override
    public void setQuestionVisibility(boolean visibility) {
        if(visibility){
            question.setVisibility(View.VISIBLE);
        }else{
            question.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClickNextFragButton() {
        mPresenter.changeFragment();
    }

    @Override
    public void onClickNextQuestionButton() {
        mPresenter.loadNextQuestion();
    }

    @Override
    public void updateQuestionText(String text) {
        question.setText(text);

    }

    @Override
    public RelativeLayout getButtonFromText(String text) {
        if (getButtonText(quizBtn1).equals(text)) {
            return quizBtn1;
        } else if (getButtonText(quizBtn2).equals(text)) {
            return quizBtn2;
        } else if (getButtonText(quizBtn3).equals(text)) {
            return quizBtn3;
        } else if (getButtonText(quizBtn4).equals(text)) {
            return quizBtn4;
        }
        return null;
    }

    public void resetOptionColors(){
//        quizBtn1.setBackgroundColor(Color.WHITE);
//        quizBtn2.setBackgroundColor(Color.WHITE);
//        quizBtn3.setBackgroundColor(Color.WHITE);
//        quizBtn4.setBackgroundColor(Color.WHITE);
    }

    public void paintCorrectAnswer(RelativeLayout layout){
        layout.setBackgroundColor(Color.rgb(18,173,42));

    }

    public void paintWrongAnswer(RelativeLayout layout){
        layout.setBackgroundColor(Color.RED);
    }

    @Override
    public void updateWrongAnswerColors(RelativeLayout wrongButton, RelativeLayout rightButton){
        paintCorrectAnswer(rightButton);
        paintWrongAnswer(wrongButton);
    }

    public void updateRightAnswerColors(RelativeLayout rightButton){
        paintCorrectAnswer(rightButton);
    }


}
