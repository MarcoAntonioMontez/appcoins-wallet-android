package com.example.quiz.quiz.quizObjects;

import android.widget.TextView;

public class rewardAdder {
    TextView rewardText;
    double preReward=0;
    double additionalReward;

    rewardAdder(TextView rewardText, double preReward, double additionalReward){
        this.rewardText=rewardText;
        this.preReward=preReward;
        this.additionalReward=additionalReward;
    }



}
