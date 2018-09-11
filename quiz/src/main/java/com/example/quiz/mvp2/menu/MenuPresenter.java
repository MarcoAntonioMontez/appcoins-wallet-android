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

    @Override
    public void setRewardMenuText() {
        String str="Congratulations!\nYou won "+ rewardSaver.getTotalScore() + " APPC." + "\nShare your results with you\nfriends and earn even more";
        menuContractView.setRewardText(str);
    }

    @Override
    public void setMenuDisabledText() {
        String str="Today your total earning was: "+ rewardSaver.getTotalScore() + " APPC." + "\nPlease comeback tomorrow to earn\neven more AppCoins";
        menuContractView.setRewardText(str);
    }

    public void updateViewRewardText(){
        String str="" + rewardSaver.getTotalScore() + " APPC";
        menuContractView.setRewardText(str);
    }

    @Override
    public void start() {

    }
}
