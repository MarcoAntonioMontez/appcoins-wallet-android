package com.example.quiz.mvp;

import java.util.Random;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private String[] helloTexts = {"Bonjour", "Hola", "Hallo" , "Merhaba", "Hello", "Ciao", "Ola", "Konnichiwa"};

    @Override
    public void loadHelloText() {
        Random random = new Random();
        String hello = helloTexts[random.nextInt(helloTexts.length)];
        getView().onTextLoaded(hello);
    }
}
