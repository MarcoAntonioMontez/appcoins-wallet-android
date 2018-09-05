package com.example.quiz.quiz.quizObjects;

import com.example.quiz.util.MathUtilsFunc;

public class RewardSaver {
    private double TotalReward=0;
    private double quizScore;

    public void setReward(double TotalReward){
        this.TotalReward=TotalReward;
    }

    public double getReward(){
        return TotalReward;
    }

    public void setQuizScore(double quizScore){
        this.quizScore=quizScore;
    }

    public double getQuizTotalScore(){
        return quizScore*TotalReward;
    }

    public double getTotalScore(){
        return MathUtilsFunc.roundTwoDecimals((quizScore+(double)(1.0)) * TotalReward);
    }
    public void addReward(double newReward){
        TotalReward=TotalReward+newReward;
    }

}
