package com.example.quiz.mvp2.quiz;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.wheel.WheelContract;
import com.example.quiz.quiz.quizObjects.Question;

import java.util.LinkedList;

public class QuizPresenter  implements QuizContract.Presenter {
    QuizContract.View quizContractView;
    FragmentNavigator.Activity fragmentNavigator;
    private LinkedList<Question> questionsList = new LinkedList<Question>();
    private Question currQuestion;
    private boolean hasQuestions=false;

    @Override
    public void changeFragment() {
         fragmentNavigator.setWheelFragment();
    }

    @Override
    public void loadNextQuestion() {
        if(!questionsList.isEmpty()){
            currQuestion=questionsList.remove();
            quizContractView.updateQuestionText(currQuestion);
            hasQuestions=true;
        }
        hasQuestions=false;
    }

    @Override
    public boolean hasQuestions() {
        return hasQuestions;
    }

    @Override
    public void start() {

    }

    public QuizPresenter(QuizContract.View quizContractView, FragmentNavigator.Activity fragmentNavigator){
        this.quizContractView=quizContractView;
        this.fragmentNavigator=fragmentNavigator;
        quizContractView.setPresenter(this);

        questionsList=loadQuestions();

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
