package com.example.quiz.mvp2.menu;

import com.example.quiz.mvp2.FragmentNavigator;
import com.example.quiz.mvp2.quiz.QuizContract;
import com.example.quiz.quiz.quizObjects.RewardSaver;

public class MenuPresenter implements MenuContract.Presenter{
    MenuContract.View menuContractView;
    FragmentNavigator.Activity fragmentNavigator;
    RewardSaver rewardSaver;

    public MenuPresenter(MenuContract.View menuContractView, FragmentNavigator.Activity fragmentNavigator, RewardSaver rewardSaver){
        this.menuContractView=menuContractView;
        this.fragmentNavigator=fragmentNavigator;
        this.rewardSaver=rewardSaver;
        menuContractView.setPresenter(this);

    }

    @Override
    public void onLoad() {
        updateViewRewardText();
    }

    public void updateViewRewardText(){
        String str="" + rewardSaver.getTotalScore() + " APPC";
        menuContractView.setRewardText(str);
    }

    @Override
    public void start() {

    }
}
