package com.example.lmont.iceicebb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lmont.iceicebb.Game;
import com.example.lmont.iceicebb.IcebreakerDBHelper;
import com.example.lmont.iceicebb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeelingLuckyFragment extends Fragment {

    Button feelingLucky;
    Game.Question question;
    TextView questionView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeling_lucky, container, false);

        setup(view);
        return view;

    }


    public void setup(View view) {

        IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
        question = dbHelper.getRandomQuestion(true);

        questionView = (TextView) view.findViewById(R.id.questionView);
        questionView.setText(question.text);


        feelingLucky = (Button) view.findViewById(R.id.feeling_lucky_btn);
        feelingLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(true);
                questionView.setText(question.text);
            }
        });

    }


}
