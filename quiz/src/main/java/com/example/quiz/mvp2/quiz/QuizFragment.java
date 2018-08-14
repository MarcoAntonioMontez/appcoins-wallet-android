package com.example.quiz.mvp2.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.quiz.quizObjects.Question;

import java.util.LinkedList;


public class QuizFragment extends Fragment implements QuizContract.View{
    LottieAnimationView animationView;
    Button confirmButton;
    Button nextQuestionButton;
    Button changeFragBtn;
    TextView question;
    RadioButton option_1;
    RadioButton option_2;
    RadioButton option_3;
    RadioButton option_4;
    RadioGroup radioGroup;
    TextView text_result;
    Question currQuestion;
    LinkedList<Question> questionList;
    private final String TAG = "QuizFragment";
    QuizContract.Presenter mPresenter;

    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        mPresenter=presenter;
    }

        @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        confirmButton = (Button) view.findViewById(R.id.confirm_button);
        nextQuestionButton = (Button) view.findViewById(R.id.nextBtn);
        changeFragBtn = (Button) view.findViewById(R.id.next_frag_btn);
        question = (TextView) view.findViewById(R.id.question_text);
        option_1 = (RadioButton) view.findViewById(R.id.option1_quiz);
        option_2 = (RadioButton) view.findViewById(R.id.option2_quiz);
        option_3 = (RadioButton) view.findViewById(R.id.option3_quiz);
        option_4 = (RadioButton) view.findViewById(R.id.option4_quiz);
        text_result = (TextView) view.findViewById(R.id.text_result);
        radioGroup = (RadioGroup) view.findViewById(R.id.optionGroup);


            mPresenter.loadNextQuestion();


            confirmButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                RadioButton checkedButton = null;
                if (option_1.isChecked()) {
                    checkedButton = option_1;
                } else if (option_2.isChecked()) {
                    checkedButton = option_2;
                } else if (option_3.isChecked()) {
                    checkedButton = option_3;
                } else if (option_4.isChecked()) {
                    checkedButton = option_4;
                }

                if (checkedButton != null) {
                    mPresenter.loadAnswerTextNButtons(checkedButton.getText().toString());
                }
                }
            });

            nextQuestionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickNextQuestionButton();
                }
            });

            changeFragBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickNextFragButton();
                }
            });



        return view;
    }

    public void hideAll() {
        confirmButton.setVisibility(View.INVISIBLE);
        nextQuestionButton.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        option_1.setVisibility(View.INVISIBLE);
        option_2.setVisibility(View.INVISIBLE);
        option_3.setVisibility(View.INVISIBLE);
        option_4.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        text_result.setVisibility(View.INVISIBLE);

    }
    public void updateText(TextView line, String str){
        line.setText(str);
    }

    public void updateQuestionText(Question newQuestion){
        LinkedList<String> optionsList = newQuestion.getOptionsList();
        question.setText(newQuestion.getQuestion());

        option_1.setText(optionsList.get(0));
        option_2.setText(optionsList.get(1));
        option_3.setText(optionsList.get(2));
        option_4.setText(optionsList.get(3));
    }

    @Override
    public void updateAnswerText(String text) {
        updateText(text_result, text);
    }

    public void initRadioGroup(){
        radioGroup.clearCheck();
    }

    @Override
    public void setNextButtonVisibility(boolean visibility) {
        if(visibility){
            nextQuestionButton.setVisibility(View.VISIBLE);
        }else{
            nextQuestionButton.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void setChangeFragButtonVisibility(boolean visibility) {
        if(visibility){
            changeFragBtn.setVisibility(View.VISIBLE);
        }else{
            changeFragBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setAnswerVisibility(boolean visibility) {
        if(visibility){
            text_result.setVisibility(View.VISIBLE);
        }else{
            text_result.setVisibility(View.INVISIBLE);
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


}
