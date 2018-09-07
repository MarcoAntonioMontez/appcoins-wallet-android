package com.example.quiz.mvp2.menu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.mvp2.quiz.QuizContract;

import org.w3c.dom.Text;

public class MenuWheelFragment extends Fragment implements MenuContract.View  {

    private MainActivity myActivity;
    TextView playBtn;
    ImageView menuImg;
    MenuContract.Presenter mPresenter;
    TextView rewardText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_wheel, container, false);

        myActivity= (MainActivity) getActivity();
        rewardText = (TextView) view.findViewById(R.id.menu_wheel_reward_text);

        playBtn= (TextView) view.findViewById(R.id.play_btn_menu_wheel);
        menuImg = (ImageView) view.findViewById(R.id.menu_wheel_img);

        mPresenter.onLoad();

        playBtn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loadNextFragment();
                    }
                }
        );

        menuImg.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loadNextFragment();
                    }
                }
        );

        return view;
    }

    public void loadNextFragment(){
        myActivity.setWheelFragment();
    }


    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setRewardText(String str) {
        rewardText.setText(str);
    }
}