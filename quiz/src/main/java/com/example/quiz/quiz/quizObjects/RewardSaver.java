package com.example.quiz.quiz.quizObjects;

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
        return (quizScore+1.0) * wheelReward;
    }

}
