package com.example.quiz.mvp2.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;

public class WheelMenuDisabledFragment extends Fragment implements MenuContract.View{

    private MainActivity myActivity;
    TextView playBtn;
    ImageView menuImg;
    MenuContract.Presenter mPresenter;
    TextView rewardText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_wheel_disabled, container, false);

        myActivity= (MainActivity) getActivity();
        rewardText = (TextView) view.findViewById(R.id.menu_wheel_disabled_reward_text);

        mPresenter.onLoad();

        return view;
    }

    public void loadNextFragment(){
        //myActivity.setQuizFragment();
    }

    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
        mPresenter=presenter;
    }

    public void setRewardText(String str) {
        rewardText.setText(str);
    }

}
