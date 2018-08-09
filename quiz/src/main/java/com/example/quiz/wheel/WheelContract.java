package com.example.quiz.wheel;

public interface WheelContract {
    interface WheelView{
        void onWheelButtonClicked();
        void onNextButtonClicked();
        void displayReward(String str);
    }

    interface WheelPresenter{
        void loadWheel();
        void spinWheel();
    }

}
