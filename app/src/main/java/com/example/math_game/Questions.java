package com.example.math_game;

public class Questions  {
    String question;
    String option1;
    String option2;
    String textA;
    String textB;

    int correctAns;

    public Questions(String question, String option1, String option2, int correctAns, String textA, String textB) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.textA = textA;
        this.textB = textB;

        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }


    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }


    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getCorrectAns() { return correctAns; }
    public void setCorrectAns(int correctAns) { this.correctAns = correctAns; }

    public String getTextA() {
        return textA;
    }
    public void setTextA(String textA) { this.textA = textA; }


    public String getTextB() {
        return textB;
    }
    public void setTextB(String textB) { this.textB = textB; }



}
