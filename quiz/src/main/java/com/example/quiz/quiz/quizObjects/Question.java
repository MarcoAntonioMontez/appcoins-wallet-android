package com.example.quiz.quiz.quizObjects;

import java.util.*;

public class Question {
    private LinkedList<String> optionsList;
    private String question;
    private String answer;

    public Question(LinkedList<String> questionsList, String question, String answer){
        this.optionsList=questionsList;
        this.question=question;
        this.answer=answer;
    }

    private boolean checkInitialParameters(){
        //Checks if answer and questionsList!=NULL
        //checks if answer is in questionsList
        return true;
    }

    public boolean checkAnswer(String answer){
        //checks answer
        return false;
    }

    public String getAnswer() {
        return answer;
    }

    public LinkedList<String> getOptionsList() {
        return optionsList;
    }

    public String getQuestion() {
        return question;
    }
}
