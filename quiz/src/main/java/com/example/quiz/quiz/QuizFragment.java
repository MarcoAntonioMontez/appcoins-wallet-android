package com.example.quiz.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.quiz.quizObjects.Question;

import java.lang.reflect.Array;
import java.util.*;
import android.widget.TextView;

public class QuizFragment extends Fragment{
    LottieAnimationView animationView;
    Button button;
    TextView question;
    TextView option_1;
    TextView option_2;
    TextView option_3;
    TextView option_4;

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        question = (TextView) view.findViewById(R.id.question_text);
        option_1 = (TextView) view.findViewById(R.id.option1_quiz);
        option_2 = (TextView) view.findViewById(R.id.option2_quiz);
        option_3 = (TextView) view.findViewById(R.id.option3_quiz);
        option_4 = (TextView) view.findViewById(R.id.option4_quiz);



        LinkedList<String> optionsList= new LinkedList<String>();
        String question = "What currency did Aptoide create?";
        String answer ="Appcoin";

        optionsList.add("Bitcoin");
        optionsList.add("Ethereum");
        optionsList.add("Litecoin");
        optionsList.add("Appcoin");

        Question newQuestion = new Question(optionsList,question,answer);

        updateText(newQuestion);



        return view;
    }

    public void updateText(Question newQuestion){
        LinkedList<String> optionsList = newQuestion.getOptionsList();
        question.setText(newQuestion.getQuestion());

        option_1.setText(optionsList.remove());
        option_2.setText(optionsList.remove());
        option_3.setText(optionsList.remove());
        option_4.setText(optionsList.remove());
    }

}
