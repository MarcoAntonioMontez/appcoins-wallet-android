package com.example.quiz.quiz;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.*;



import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.quiz.quizObjects.Question;

import java.lang.reflect.Array;
import java.util.*;
import android.widget.TextView;

import org.w3c.dom.Text;


public class QuizFragment extends Fragment{
    LottieAnimationView animationView;
    Button confirmButton;
    Button nextButton;
    TextView question;
    RadioButton option_1;
    RadioButton option_2;
    RadioButton option_3;
    RadioButton option_4;
    TextView text_result;
    Question currQuestion;
    LinkedList<Question> questionList;




    private final String TAG = "QuizFragment";

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        confirmButton = (Button) view.findViewById(R.id.confirm_button);
        nextButton = (Button) view.findViewById(R.id.nextBtn);
        question = (TextView) view.findViewById(R.id.question_text);
        option_1 = (RadioButton) view.findViewById(R.id.option1_quiz);
        option_2 = (RadioButton) view.findViewById(R.id.option2_quiz);
        option_3 = (RadioButton) view.findViewById(R.id.option3_quiz);
        option_4 = (RadioButton) view.findViewById(R.id.option4_quiz);
        text_result = (TextView) view.findViewById(R.id.text_result);





        questionList = loadQuestions();


        currQuestion=questionList.remove();
        updateQuestionText(currQuestion);

        confirmButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                RadioButton checkedButton=null;
                if(option_1.isChecked()){
                    checkedButton=option_1;
                } else if(option_2.isChecked()){
                    checkedButton=option_2;
                }
                else if(option_3.isChecked()){
                    checkedButton=option_3;
                }
                else if(option_4.isChecked()){
                    checkedButton=option_4;
                }

                if(checkedButton!=null) {
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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!questionList.isEmpty()) {
                    currQuestion = questionList.remove();
                    updateQuestionText(currQuestion);
                    text_result.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                } else{
                     hideAll();
                     updateText(text_result, "Quiz Completed!!!");
                     text_result.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    private void hideAll() {
        confirmButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        option_1.setVisibility(View.INVISIBLE);
        option_2.setVisibility(View.INVISIBLE);
        option_3.setVisibility(View.INVISIBLE);
        option_4.setVisibility(View.INVISIBLE);
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


    private LinkedList<Question> createQuestions(){
        LinkedList<Question> questionList = new LinkedList<Question>();
        LinkedList<String> optionsList;
        String textQuestion;
        String answer;
        Question question;

        // 1st Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="What is the name of the famous Bitcoin exchange from japan that collapsed in 2014?";
        optionsList.add("Blockchain.info");
        optionsList.add("Tradehill");
        optionsList.add("Mt.Gox");
        optionsList.add("Bitstamp");
        answer="Mt.Gox";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        // 2nd Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="Which of the following is popularly used for storing bitcoins?";
        optionsList.add("Pocket");
        optionsList.add("Wallet");
        optionsList.add("Box");
        optionsList.add("Stack");
        answer="Wallet";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        // 3rd Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="What does P2P stand for?";
        optionsList.add("Password to Password");
        optionsList.add("Peer to Peer");
        optionsList.add("Product to Product");
        optionsList.add("Private Key to Public Key");
        answer="Peer to Peer";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        return questionList;
    }

    private LinkedList<Question> loadQuestions(){
        LinkedList<Question> questionList = createQuestions();

        return questionList;
    }

}
