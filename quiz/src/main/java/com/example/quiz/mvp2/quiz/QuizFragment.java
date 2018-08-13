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
    Button nextButton;
    Button changeBtn;
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
    boolean showQuestion;

    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        mPresenter=presenter;
    }

        @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        confirmButton = (Button) view.findViewById(R.id.confirm_button);
        nextButton = (Button) view.findViewById(R.id.nextBtn);
        changeBtn = (Button) view.findViewById(R.id.next_frag_btn);
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
                        if (checkedButton.getText() == currQuestion.getAnswer()) {
                            updateText(text_result, "Correct Answer!");
                            nextButton.setVisibility(View.VISIBLE);
                        } else {
                            updateText(text_result, "Incorrect Answer!");
                        }
                        text_result.setVisibility(View.VISIBLE);

                    }
                }
            });

            //activityCommander.gotoQuizFrag();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPresenter.hasQuestions()) {
                    radioGroup.clearCheck();
                    mPresenter.loadNextQuestion();
                    text_result.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                } else{
                     hideAll();
                     updateText(text_result, "Quiz Completed!!!");
                     text_result.setVisibility(View.VISIBLE);
                     changeBtn.setVisibility(View.VISIBLE);

                    changeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPresenter.changeFragment();
                            //activityCommander.gotoWheelFrag();
                        }
                    });
                }
            }
        });



        return view;
    }

    private void hideAll() {
        confirmButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        //option_1.setVisibility(View.INVISIBLE);
        //option_2.setVisibility(View.INVISIBLE);
        //option_3.setVisibility(View.INVISIBLE);
        //option_4.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        text_result.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

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
    public void onClickNextButton() {
        mPresenter.changeFragment();
    }
}
