package com.example.quiz.quiz.quizObjects;

import com.example.quiz.util.MathUtilsFunc;

public class RewardSaver {
    private double wheelReward=0;
    private double totalReward=0;
    private double quizScore;

    public void setReward(double totalReward){
        this.wheelReward=totalReward;
        this.totalReward=totalReward;
    }

    public double getWheelReward(){
        return wheelReward;
    }

    public void setQuizScore(double quizScore){
        this.quizScore=quizScore;
    }

    public double getQuizTotalScore(){
        return quizScore*totalReward;
    }

    public double getTotalScore(){
        return MathUtilsFunc.roundTwoDecimals((quizScore+(double)(1.0)) * totalReward);
    }
    public void addReward(){
        totalReward=totalReward+wheelReward;
    }
    public void addReward(double newReward){
        totalReward=totalReward+newReward;
    }

}
