package com.example.quiz.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;

public class QuizFragment extends Fragment{
    LottieAnimationView animationView;
    Button button;

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wheel_fragment, container, false);


        animationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        button = (Button) view.findViewById(R.id.button_wheel);
        animationView.setAnimation("wheel.json");
        button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                animationView.setRepeatCount(1);
                animationView.playAnimation();
            }
        });
        return view;
    }

}
