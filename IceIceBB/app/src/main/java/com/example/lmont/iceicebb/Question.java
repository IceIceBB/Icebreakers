package com.example.lmont.iceicebb;

/**
 * Created by lmont on 9/25/2016.
 */
public class Question {
    // To create a new question, for now, just use postman to do a post to
    // https://floating-island-55807.herokuapp.com/questions

    public String name;
    public String text;
    public boolean sfw;

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", sfw=" + sfw +
                '}';
    }
}
