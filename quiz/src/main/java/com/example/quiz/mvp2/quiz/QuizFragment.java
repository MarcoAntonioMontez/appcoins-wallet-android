package com.example.quiz.mvp2.quiz;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quiz.R;
import com.example.quiz.mvp2.MainActivity;
import com.example.quiz.quiz.quizObjects.Question;
import com.example.quiz.quiz.quizObjects.RewardSaver;
import com.example.quiz.util.MathUtilsFunc;

import org.w3c.dom.Text;

import java.util.LinkedList;


public class QuizFragment extends Fragment implements QuizContract.View{
    ImageView nextQuestionButton;
    TextView question;
    TextView text_result;
    TextView wheelScoreText;
    TextView quizScoreText;
    TextView totalScoreText;


    Question currQuestion;
    LinkedList<Question> questionList;
    private final String TAG = "QuizFragment";
    private QuizContract.Presenter mPresenter;
    private MainActivity myActivity;
    private RewardSaver rewardSaver;
    private TextView quizBtnText1;
    private TextView quizBtnText2;
    private TextView quizBtnText3;
    private TextView quizBtnText4;
    private int idWhiteRectangleBackground;
    private int idRedRectangleBackground;
    private int idGrayRectangleBackground;
    private int idGreenRectangleBackground;
    private int whiteText;
    private int grayText;
    private int blackText;
    private RelativeLayout timerOutScreen;
    private Button timerNextBtn;
    LottieAnimationView timerAnimation;


    //RewardAdder
    long timeInMilliseconds= 1000; //milisec
    final long timeDivisions=50;
    long tickTime = (timeInMilliseconds/timeDivisions);
    double currentReward;


    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        mPresenter=presenter;
    }

        @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        question = (TextView) view.findViewById(R.id.question_text_quiz);
        totalScoreText = (TextView) view.findViewById(R.id.cur_prize_text);
        quizBtnText1 = (TextView) view.findViewById(R.id.quiz_btn_text_1);
        quizBtnText2 = (TextView) view.findViewById(R.id.quiz_btn_text_2);
        quizBtnText3 = (TextView) view.findViewById(R.id.quiz_btn_text_3);
        quizBtnText4 = (TextView) view.findViewById(R.id.quiz_btn_text_4);

        idWhiteRectangleBackground = (int) R.drawable.rectangle_white;
        idRedRectangleBackground = (int) R.drawable.rectangle_red;
        idGrayRectangleBackground = (int) R.drawable.rectangle_gray;
        idGreenRectangleBackground = (int) R.drawable.rectangle_green;

        whiteText = Color.WHITE;
        grayText = Color.BLACK;
        blackText = Color.BLACK;

        timerOutScreen= (RelativeLayout) view.findViewById(R.id.timer_out_screen);
        timerNextBtn = (Button) view.findViewById(R.id.next_timer_btn);

        timerAnimation = (LottieAnimationView) view.findViewById(R.id.timer_animation);
        timerAnimation.setAnimation("timer-final.json");

        myActivity= (MainActivity) getActivity();
        rewardSaver=myActivity.getRewardSaver();

        currentReward=rewardSaver.getTotalScore();
        mPresenter.loadNextQuestion();

        timerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadQuestionsFromTimerButton();
            }
        });


        quizBtnText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtnText1);
            }
        });

        quizBtnText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtnText2);

            }
        });

        quizBtnText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtnText3);

            }
        });

        quizBtnText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadAnswer(quizBtnText4);

            }
        });

        timerAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mPresenter.onTimerEnd();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        return view;
    }

    public void runTimerAnimation(){
        timerAnimation.playAnimation();
    }

    public void pauseTimerAnimation(){
        timerAnimation.pauseAnimation();
    }

    @Override
    public String getButtonText(TextView button){
        if(button!=null){
            return button.getText().toString();
        }
        return null;
    }

    public void hideAll() {
        nextQuestionButton.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        quizBtnText1.setVisibility(View.INVISIBLE);
        quizBtnText2.setVisibility(View.INVISIBLE);
        quizBtnText3.setVisibility(View.INVISIBLE);
        quizBtnText4.setVisibility(View.INVISIBLE);
        text_result.setVisibility(View.INVISIBLE);

    }
    public void updateText(TextView line, String str){
        line.setText(str);
    }

    public void updateQuestionTextAndOptions(Question newQuestion){
        LinkedList<String> optionsList = newQuestion.getOptionsList();
        question.setText(newQuestion.getQuestion());

        quizBtnText1.setText(optionsList.get(0));
        quizBtnText2.setText(optionsList.get(1));
        quizBtnText3.setText(optionsList.get(2));
        quizBtnText4.setText(optionsList.get(3));
    }


    @Override
    public void setEndScreenRewardText(String wheelScoreText, String quizScoreText, String totalScoreText) {
       this.wheelScoreText.setText(wheelScoreText);
       this.quizScoreText.setText(quizScoreText);
       this.totalScoreText.setText(totalScoreText);
    }

    public void setEndScreenTextVisibility(boolean visibility){
        if(visibility){
            wheelScoreText.setVisibility(View.VISIBLE);
            quizScoreText.setVisibility(View.VISIBLE);
            totalScoreText.setVisibility(View.VISIBLE);
        }else{
            wheelScoreText.setVisibility(View.INVISIBLE);
            quizScoreText.setVisibility(View.INVISIBLE);
            totalScoreText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClickNextFragButton() {
        mPresenter.changeFragment();
    }

    @Override
    public void onClickNextQuestionButton() {
        mPresenter.loadNextQuestion();
    }

    @Override
    public void updateQuestionText(String text) {
        question.setText(text);

    }

    @Override
    public TextView getButtonFromText(String text) {
        if (getButtonText(quizBtnText1).equals(text)) {
            return quizBtnText1;
        } else if (getButtonText(quizBtnText2).equals(text)) {
            return quizBtnText2;
        } else if (getButtonText(quizBtnText3).equals(text)) {
            return quizBtnText3;
        } else if (getButtonText(quizBtnText4).equals(text)) {
            return quizBtnText4;
        }
        return null;
    }

    public void resetOptionColors(){
//        quizBtn1.setBackgroundColor(Color.WHITE);
//        quizBtn2.setBackgroundColor(Color.WHITE);
//        quizBtn3.setBackgroundColor(Color.WHITE);
//        quizBtn4.setBackgroundColor(Color.WHITE);
    }

    public void paintCorrectAnswer(TextView quizButton){
        quizButton.setBackground(getResources().getDrawable(idGreenRectangleBackground));
        quizButton.setTextColor(whiteText);

    }

    public void paintWrongAnswer(TextView quizButton){
        quizButton.setBackground(getResources().getDrawable(idRedRectangleBackground));
        quizButton.setTextColor(whiteText);
    }

    @Override
    public void updateWrongAnswerColors(TextView wrongButton, TextView rightButton){
        updateButtonsColorWhenAnswered();
        paintCorrectAnswer(rightButton);
        paintWrongAnswer(wrongButton);
    }

    public void updateRightAnswerColors(TextView rightButton){
        updateButtonsColorWhenAnswered();
        paintCorrectAnswer(rightButton);
    }

    public void setRewardText(String text){
        totalScoreText.setText(text);
    }

    public void showRewardAdder(final double reward, final TextView textview){


        CountDownTimer countDownTimer = new CountDownTimer(timeInMilliseconds, tickTime) {
            @Override
            public void onTick(long l) {
                currentReward=currentReward+(reward/(double)timeDivisions);
                textview.setText("" + MathUtilsFunc.roundTwoDecimals(currentReward) + " APPC" );
            }

            @Override
            public void onFinish() {
                currentReward=rewardSaver.getTotalScore();
                String endReward="" + MathUtilsFunc.roundTwoDecimals(currentReward) + " APPC";
                textview.setText(endReward);
            }
        }.start();
    }

    public TextView getTextView(String text){
        if(text.equals("totalScoreText")){
            return totalScoreText;
        } else if (text.equals("coinsText")){
            return null;
        }
        return null;
    }

    public void updateButtonsColorWhenAnswered(){
        quizBtnText1.setBackground(getResources().getDrawable(idGrayRectangleBackground));
        quizBtnText2.setBackground(getResources().getDrawable(idGrayRectangleBackground));
        quizBtnText3.setBackground(getResources().getDrawable(idGrayRectangleBackground));
        quizBtnText4.setBackground(getResources().getDrawable(idGrayRectangleBackground));

        quizBtnText1.setTextColor(grayText);
        quizBtnText2.setTextColor(grayText);
        quizBtnText3.setTextColor(grayText);
        quizBtnText4.setTextColor(grayText);
    }

    public void resetButtonsColor(){
        quizBtnText1.setBackground(getResources().getDrawable(idWhiteRectangleBackground));
        quizBtnText2.setBackground(getResources().getDrawable(idWhiteRectangleBackground));
        quizBtnText3.setBackground(getResources().getDrawable(idWhiteRectangleBackground));
        quizBtnText4.setBackground(getResources().getDrawable(idWhiteRectangleBackground));

        quizBtnText1.setTextColor(blackText);
        quizBtnText2.setTextColor(blackText);
        quizBtnText3.setTextColor(blackText);
        quizBtnText4.setTextColor(blackText);
    }



    @Override
    public void setTimerOutScreenVisibility(boolean visibility) {
        if(visibility){
            timerOutScreen.setVisibility(View.VISIBLE);
        }else{
            timerOutScreen.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean isTimerOutOnScreen() {
        if(timerOutScreen.getVisibility()==View.VISIBLE)  {
            return true;
        }
        return false;
    }

    @Override
    public void setQuizButtonsClickable(boolean isClickable) {
        if(isClickable){
            quizBtnText1.setClickable(true);
            quizBtnText2.setClickable(true);
            quizBtnText3.setClickable(true);
            quizBtnText4.setClickable(true);
        }else{
            quizBtnText1.setClickable(false);
            quizBtnText2.setClickable(false);
            quizBtnText3.setClickable(false);
            quizBtnText4.setClickable(false);
        }
    }

}
