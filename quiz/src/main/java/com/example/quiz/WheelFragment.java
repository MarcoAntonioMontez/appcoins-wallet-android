package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.wheel.FragmentCallBack;

import android.animation.*;
import android.widget.TextView;

public class WheelFragment extends Fragment {
    LottieAnimationView animationView;
    Button button;
    TextView rewardText;

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);

        rewardText=(TextView) view.findViewById(R.id.reward_text);
        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        button = (Button) view.findViewById(R.id.button_wheel);
        animationView.setAnimation("wheel.json");

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rewardText.setText("Congratulations you won 1 Apc!");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

                button.setOnClickListener( new View.OnClickListener(){
                    public void onClick(View v){
                        animationView.setRepeatCount(1);
                        animationView.playAnimation();
                    }
                });
        return view;
    }

}
