package com.example.quiz.mvp2.quiz;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.mvp2.wheel.WheelContract;
import com.example.quiz.quiz.quizObjects.Question;
import com.example.quiz.quiz.quizObjects.RewardSaver;

import java.util.LinkedList;

public class QuizPresenter  implements QuizContract.Presenter {
    QuizContract.View quizContractView;
    FragmentNavigator.Activity fragmentNavigator;
    private LinkedList<Question> questionsList = new LinkedList<Question>();
    private Question currQuestion;
    int score=0;
    int numberAnswers=3;
    boolean firstAnswerAttempt=false;
    RewardSaver rewardSaver;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 20000; // 20 seg
    private boolean timerRunning=true;
    private boolean isQuestionAnswered=false;
    final Handler handler= new Handler();

    double fixedQuizReward=0.3;
    int quizBufferTime=2000;
    private int question_id=0;


    @Override
    public void changeFragment() {
         fragmentNavigator.setRewardMenuFragment();
    }

    @Override
    public void onTimerEnd(){
        quizContractView.setTimerOutScreenVisibility(true);
        quizContractView.setQuizButtonsClickable(false);
    }


    public void onNextQuestion(){
        quizContractView.runTimerAnimation();
        currQuestion=questionsList.remove();
        quizContractView.resetOptionColors();
        quizContractView.updateQuestionTextAndOptions(currQuestion);
        updateQuestionIndex();

    }

    public void updateQuestionIndex(){
        question_id++;
        quizContractView.colorQuestionIndex(question_id);
    }

    @Override
    public Question loadNextQuestion() {
        quizContractView.resetButtonsColor();
        quizContractView.setRewardText("" + rewardSaver.getTotalScore() + " APPC");
        isQuestionAnswered=false;
        firstAnswerAttempt=true;
        if(!questionsList.isEmpty()){
            onNextQuestion();
        }else{
            changeFragment();
            currQuestion=null;
        }
        return currQuestion;
    }

    @Override
    public void loadAnswer(TextView chosenButton) {
        String chosenOption=chosenButton.getText().toString();
        quizContractView.pauseTimerAnimation();
        if(!isQuestionAnswered){
            if(currQuestion!=null){
                if(currQuestion.getAnswer().equals(chosenOption)){
                    quizContractView.updateRightAnswerColors(chosenButton);
                    score++;
                    isQuestionAnswered=true;
                    rewardSaver.addReward();
                    quizContractView.showRewardAdder(rewardSaver.getWheelReward(), quizContractView.getTextView("totalScoreText"));
                    quizContractView.runCoinsAnimation();

                }else {
                    TextView rightButton = quizContractView.getButtonFromText(currQuestion.getAnswer());
                    quizContractView.updateWrongAnswerColors(chosenButton,rightButton);
                    isQuestionAnswered=true;
                }
                loadNextQuestionAfterTime(quizBufferTime);
            }
        }

    }

    public void loadNextQuestionAfterTime(int time){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNextQuestion();
            }
        }, time);
    }

    @Override
    public void start() {

    }


    public QuizPresenter(QuizContract.View quizContractView, FragmentNavigator.Activity fragmentNavigator, RewardSaver rewardSaver){
        this.quizContractView=quizContractView;
        this.fragmentNavigator=fragmentNavigator;
        this.rewardSaver=rewardSaver;
        quizContractView.setPresenter(this);

        questionsList=loadQuestions();

    }
    private LinkedList<Question> loadQuestions(){
        LinkedList<Question> questionList = createQuestions();

        return questionList;
    }

    @Override
    public boolean isQuestionAnswered() {
        return isQuestionAnswered;
    }

    @Override
    public void loadQuestionsFromTimerButton() {
        loadNextQuestion();
        quizContractView.setTimerOutScreenVisibility(false);
        quizContractView.setQuizButtonsClickable(true);
    }

    private LinkedList<Question> createQuestions(){
        LinkedList<Question> questionList = new LinkedList<Question>();
        LinkedList<String> optionsList;
        String textQuestion;
        String answer;
        Question question;

//        // 1st Question
//        //Initialize  optionsList
//        optionsList = new LinkedList<String>();
//        //Write the question, options and answer
//        textQuestion="What is the name of the famous Bitcoin exchange from japan that collapsed in 2014?";
//        optionsList.add("Blockchain.info");
//        optionsList.add("Tradehill");
//        optionsList.add("Mt.Gox");
//        optionsList.add("Bitstamp");
//        answer="Mt.Gox";
//
//        //Add the question to the list
//        question = new Question(optionsList, textQuestion, answer);
//        questionList.add(question);
//
//        // 2nd Question
//        //Initialize  optionsList
//        optionsList = new LinkedList<String>();
//        //Write the question, options and answer
//        textQuestion="Which of the following is popularly used for storing bitcoins?";
//        optionsList.add("Pocket");
//        optionsList.add("Wallet");
//        optionsList.add("Box");
//        optionsList.add("Stack");
//        answer="Wallet";
//
//        //Add the question to the list
//        question = new Question(optionsList, textQuestion, answer);
//        questionList.add(question);
//
//        // 3rd Question
//        //Initialize  optionsList
//        optionsList = new LinkedList<String>();
//        //Write the question, options and answer
//        textQuestion="What does P2P stand for?";
//        optionsList.add("Password to Password");
//        optionsList.add("Peer to Peer");
//        optionsList.add("Product to Product");
//        optionsList.add("Private Key to Public Key");
//        answer="Peer to Peer";
//
//        //Add the question to the list
//        question = new Question(optionsList, textQuestion, answer);
//        questionList.add(question);

        // 4th Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="What is a node?";
        optionsList.add("A computer on a Blockchain network");
        optionsList.add("A type of cryptocurrency");
        optionsList.add("A Blockchain");
        optionsList.add("An exchange");
        answer="A computer on a Blockchain network";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        // 5th Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="What is a miner?";
        optionsList.add("A type of blockchain");
        optionsList.add("An algorithm that predicts the next part of the chain");
        optionsList.add("A person doing calculations to verify a transaction");
        optionsList.add("Computers that validate and process \nBlockchain transactions");
        answer="Computers that validate and process \nBlockchain transactions";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        // 6th Question
        //Initialize  optionsList
        optionsList = new LinkedList<String>();
        //Write the question, options and answer
        textQuestion="What is cold storage?";
        optionsList.add("A place to hang your coat");
        optionsList.add("A private key connected to the Internet");
        optionsList.add("An offline private key");
        optionsList.add("A desktop wallet");
        answer="An offline private key";

        //Add the question to the list
        question = new Question(optionsList, textQuestion, answer);
        questionList.add(question);

        return questionList;
    }

}