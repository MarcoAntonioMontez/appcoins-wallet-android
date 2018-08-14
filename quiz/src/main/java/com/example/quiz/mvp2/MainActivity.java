package com.example.quiz.mvp2;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.mvp2.quiz.QuizFragment;
import com.example.quiz.mvp2.quiz.QuizPresenter;
import com.example.quiz.mvp2.wheel.WheelContract;
import com.example.quiz.mvp2.wheel.WheelFragment;
import com.example.quiz.mvp2.wheel.WheelPresenter;


public class MainActivity extends AppCompatActivity implements FragmentNavigator.Activity{

    BasePresenter mPresenter;
    private double reward=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            // Create a new Fragment to be placed in the activity layout
            WheelFragment firstFragment = new WheelFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

            //Create the Presenter
            mPresenter = new WheelPresenter(firstFragment,this,this);
        }

    }


    @Override
    public void setQuizFragment() {
        QuizFragment newFragment = new QuizFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment,"QuizFrag");
        transaction.addToBackStack(null);

        transaction.commit();

        //Update Presenter
        mPresenter = new QuizPresenter(newFragment,this);
    }

    @Override
    public void setWheelFragment() {
        WheelFragment newFragment = new WheelFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        //Update Presenter
        mPresenter = new WheelPresenter(newFragment,this, this);
    }

    public double getRewardValue(){
        return reward;
    }

    public void saveRewardValue(double reward) {
        if(this.reward==-1){
            this.reward=reward;
        }
    }
}
