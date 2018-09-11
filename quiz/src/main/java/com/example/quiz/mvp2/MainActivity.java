package com.example.quiz.mvp2;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.mvp2.loading.LoadingFragment;
import com.example.quiz.mvp2.menu.MenuPresenter;
import com.example.quiz.mvp2.menu.MenuWheelFragment;
import com.example.quiz.mvp2.menu.QuizMenuFragment;
import com.example.quiz.mvp2.menu.RewardMenuFragment;
import com.example.quiz.mvp2.menu.WheelMenuDisabledFragment;
import com.example.quiz.mvp2.quiz.QuizFragment;
import com.example.quiz.mvp2.quiz.QuizPresenter;
import com.example.quiz.mvp2.wheel.Dummy_fragment;
import com.example.quiz.mvp2.wheel.WheelContract;
import com.example.quiz.mvp2.wheel.WheelFragment;
import com.example.quiz.mvp2.wheel.WheelFragment_v1;
import com.example.quiz.mvp2.wheel.WheelPresenter;
import com.example.quiz.quiz.quizObjects.RewardSaver;


public class MainActivity extends AppCompatActivity implements FragmentNavigator.Activity{

    BasePresenter mPresenter;
    private RewardSaver rewardSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rewardSaver = new RewardSaver();

        setContentView(R.layout.activity_quiz);
        //setContentView(R.layout.greeting_screen);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

//            // Create a new Fragment to be placed in the activity layout
//            //WheelFragment firstFragment = new WheelFragment();
//            //Dummy_fragment firstFragment = new Dummy_fragment();
//            WheelFragment_v1 firstFragment = new WheelFragment_v1();
//
//            // In case this activity was started with special instructions from an
//            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());
//
//            // Add the fragment to the 'fragment_container' FrameLayout
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, firstFragment).commit();
//
//            //Create the Presenter
//            mPresenter = new WheelPresenter(firstFragment,this,rewardSaver);

            setLoadingFragment();
        }

    }

    public RewardSaver getRewardSaver(){
        return rewardSaver;
    }

    @Override
    public void setQuizFragment() {
        QuizFragment newFragment = new QuizFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment,"QuizFrag");
        //transaction.addToBackStack(null);

        transaction.commit();

        //Update Presenter
        mPresenter = new QuizPresenter(newFragment,this, rewardSaver);
    }

    @Override
    public void setLoadingFragment() {
        LoadingFragment newFragment = new LoadingFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setMenuWheelFragment() {
        MenuWheelFragment newFragment = new MenuWheelFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        mPresenter = new MenuPresenter(newFragment,this, rewardSaver);
    }

    @Override
    public void setWheelFragment() {
        WheelFragment_v1 newFragment = new WheelFragment_v1();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        //Update Presenter
        mPresenter = new WheelPresenter(newFragment,this, rewardSaver);
    }

    @Override
    public void setQuizMenuFragment() {
        QuizMenuFragment newFragment = new QuizMenuFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        mPresenter = new MenuPresenter(newFragment,this, rewardSaver);
    }

    @Override
    public void setWheelMenuDisabled() {
        WheelMenuDisabledFragment newFragment = new WheelMenuDisabledFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        mPresenter = new MenuPresenter(newFragment,this, rewardSaver);
    }

    @Override
    public void setRewardMenuFragment() {
        RewardMenuFragment newFragment = new RewardMenuFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        mPresenter = new MenuPresenter(newFragment,this, rewardSaver);
    }


}
