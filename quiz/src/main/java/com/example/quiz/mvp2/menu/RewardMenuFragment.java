package com.example.quiz.mvp2.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;

public class RewardMenuFragment extends Fragment implements MenuContract.View {
    private MainActivity myActivity;
    TextView rewardText;
    LottieAnimationView rewardCupAnimation;
    MenuContract.Presenter mPresenter;
    Button okBtn;
    int infinite = (int)Double.POSITIVE_INFINITY;
    ImageView prevBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reward_fragment, container, false);

        myActivity = (MainActivity) getActivity();
        rewardText = (TextView) view.findViewById(R.id.reward_menu_text);
        okBtn = (Button) view.findViewById(R.id.ok_btn);
        rewardCupAnimation = (LottieAnimationView) view.findViewById(R.id.reward_cup_animation);
        prevBtn = (ImageView) view.findViewById(R.id.left_arrow_menu);

        rewardCupAnimation.setAnimation("cup-animation.json");
        rewardCupAnimation.playAnimation();
        rewardCupAnimation.setRepeatCount(infinite);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextFragment();
            }
        });

        mPresenter.setRewardMenuText();

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myActivity.finish();
            }
        });

        return view;
    }

    @Override
    public void setRewardText(String str) {
        rewardText.setText(str);
    }

    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
        mPresenter = presenter;
    }
    public void loadNextFragment() {
        myActivity.setWheelMenuDisabled();
    }
}
