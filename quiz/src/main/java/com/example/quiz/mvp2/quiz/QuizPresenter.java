package com.example.quiz.mvp2.quiz;

import android.graphics.Color;
import android.os.CountDownTimer;

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


    @Override
    public void changeFragment() {
         fragmentNavigator.setWheelFragment();
    }

    @Override
    public Question loadNextQuestion() {
        firstAnswerAttempt=true;
        quizContractView.setConfirmButtonVisibility(true);
        if(!questionsList.isEmpty()){
            startTimer();
            currQuestion=questionsList.remove();
            quizContractView.updateQuestionTextAndOptions(currQuestion);
            quizContractView.setNextButtonVisibility(false);
            quizContractView.setAnswerVisibility(false);
            quizContractView.initRadioGroup();
        }else{

            quizContractView.hideAll();
            quizContractView.setAnswerVisibility(false);

            setEndScreenText();

            quizContractView.setChangeFragButtonVisibility(true);
            currQuestion=null;
        }
        return currQuestion;
    }

    public void setEndScreenText(){
        rewardSaver.setQuizScore(score);
        String wheelText="Wheel reward: " + rewardSaver.getReward() + " Apc";
        String quizScoreText="Quiz Score: \n\n"+ "  Correct answers: "+score+" -> " + ((double)score*rewardSaver.getReward())
                + " Apc"+"\n\n  Incorrect answers: " + (numberAnswers-score) + " -> 0 Apc";
        String totalScoreText="\n\n\n\nTotal reward: "+ rewardSaver.getTotalScore()+ " Apc";


        quizContractView.setEndScreenRewardText(wheelText,quizScoreText,totalScoreText);
        quizContractView.setEndScreenTextVisibility(true);

    }

    @Override
    public void loadAnswerTextNButtons(String chosenOption) {
        if(currQuestion!=null){
            quizContractView.setAnswerVisibility(true);
            quizContractView.setNextButtonVisibility(true);
            if(currQuestion.getAnswer().equals(chosenOption)){
                quizContractView.updateAnswerText("Correct!");
                quizContractView.updateAnswerColor(Color.rgb(18,173,42));
                score++;

            }else {
                quizContractView.updateAnswerText("Wrong answer\n\nCorrect answer is: " + currQuestion.getAnswer());
                quizContractView.updateAnswerColor(Color.RED);
            }
            quizContractView.setConfirmButtonVisibility(false);
            cancelTimer();
        }

    }

    @Override
    public void start() {

    }

    public void cancelTimer(){
        countDownTimer.cancel();
        quizContractView.changeTimerText("");

    }

    public QuizPresenter(QuizContract.View quizContractView, FragmentNavigator.Activity fragmentNavigator, RewardSaver rewardSaver){
        this.quizContractView=quizContractView;
        this.fragmentNavigator=fragmentNavigator;
        this.rewardSaver=rewardSaver;
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

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                updateTimerColor((int)l/1000);
                quizContractView.changeTimerText(""+(int)l/1000);
            }

            @Override
            public void onFinish() {
                timerRunning=false;
                if(!quizContractView.isQuestionAnswered()){
                    quizContractView.setAnswerVisibility(true);
                    quizContractView.setNextButtonVisibility(true);
                    quizContractView.updateAnswerText("Time has run out :(");
                    quizContractView.updateAnswerColor(Color.RED);
                    quizContractView.setConfirmButtonVisibility(false);
                }else{
                    quizContractView.changeTimerText("End");
                }
            }
        }.start();

    }

    public void updateTimerColor(int timeLeft){
        final int startTime=19;
        final int mediumTimeLeft=10;
        final int lowTimeLeft=5;

        switch (timeLeft){

            case startTime : quizContractView.setTimerTextColor(Color.rgb(144,238,144));
                            break;

            case mediumTimeLeft : quizContractView.setTimerTextColor(Color.rgb(255,165,0));
                    break;

            case lowTimeLeft: quizContractView.setTimerTextColor(Color.RED);
                    break;

            default: break;
        }

    }

}
