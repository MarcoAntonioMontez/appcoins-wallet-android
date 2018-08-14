package com.example.quiz.mvp2;

import android.support.v4.app.Fragment;

public interface FragmentNavigator {
    interface Activity{
        void setQuizFragment();
        void setWheelFragment();
    }
}
