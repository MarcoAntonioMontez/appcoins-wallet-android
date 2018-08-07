package com.example.quiz.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;

import android.animation.*;
import android.widget.TextView;

public class WheelFragment extends Fragment {
    LottieAnimationView animationView;
    Button buttonWheel;
    Button buttonNext;
    TextView rewardText;


    NextFragListener activityCommander;

    public interface NextFragListener{
        public void changeFrag();
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            activityCommander = (NextFragListener) activity;
        } catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.reward_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        buttonWheel = (Button) view.findViewById(R.id.button_wheel);
        buttonNext = (Button) view.findViewById(R.id.button_next);
        animationView.setAnimation("wheel.json");

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rewardText.setText("Congratulations you won 1 Appc!");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        buttonWheel.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                animationView.setRepeatCount(1);
                animationView.playAnimation();
            }
        });

        buttonNext.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        activityCommander.changeFrag();
                    }
                }
        );
        return view;
    }

//    public void buttonClicked(View view){
//        activityCommander.changeFrag();
//    }

}
