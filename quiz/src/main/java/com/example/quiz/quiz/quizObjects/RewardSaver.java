package com.example.quiz.quiz.quizObjects;

import com.example.quiz.util.MathUtilsFunc;

public class RewardSaver {
    private double wheelReward;
    private double quizScore;

    public void setReward(double wheelReward){
        this.wheelReward=wheelReward;
    }

    public double getReward(){
        return wheelReward;
    }

    public void setQuizScore(double quizScore){
        this.quizScore=quizScore;
    }

    public double getQuizTotalScore(){
        return quizScore*wheelReward;
    }

    public double getTotalScore(){
        return MathUtilsFunc.roundTwoDecimals((quizScore+(double)(1.0)) * wheelReward);
    }

}
