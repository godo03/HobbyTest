package com.thesis.let.webservicedemo.classes;


import android.text.Html;

public class QuestionClass {
    Integer ID;
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;



    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionClass(Integer ID, String question, String optionA, String optionB, String optionC, String optionD, String answer, String description) {
        this.ID = ID;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;

        this.answer = answer;
    }

    public QuestionClass(String question, String optionA, String optionB, String optionC, String optionD) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    public QuestionClass(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        String output = Html.fromHtml(question) + "\n\nCorrect answer: " + Html.fromHtml(answer) + " ";
        return output;
    }
}
