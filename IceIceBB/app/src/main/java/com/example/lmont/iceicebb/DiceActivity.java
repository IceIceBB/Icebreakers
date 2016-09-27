package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

//The activity where virtual dice are simulated and rolled
public class DiceActivity extends AppCompatActivity {

    Random random = new Random();

    FrameLayout diceOne;
    ImageView diceResultOne;
    FrameLayout diceTwo;
    ImageView getDiceResultTwo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceOne = (FrameLayout) findViewById(R.id.diceOne);
        diceResultOne = (ImageView) findViewById(R.id.diceResultOne);

        diceTwo = (FrameLayout) findViewById(R.id.diceTwo);
        getDiceResultTwo = (ImageView) findViewById(R.id.diceResultTwo);

        diceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation spinAnimation = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.dice_spin);
                diceOne.startAnimation(spinAnimation);
                diceTwo.startAnimation(spinAnimation);
                diceResultOne.startAnimation(spinAnimation);
                getDiceResultTwo.startAnimation(spinAnimation);

            }
        });

        diceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation spinAnimation = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.dice_spin);
                diceOne.startAnimation(spinAnimation);
                diceTwo.startAnimation(spinAnimation);
                diceResultOne.startAnimation(spinAnimation);
                getDiceResultTwo.startAnimation(spinAnimation);
            }
        });

    }

    // A method to roll 2 dice and receive both in a single Integer[]
    private int[] rollDice(){
        int[] diceResults = new int[2];
        int rollOne = random.nextInt(6)+1;
        int rollTwo = random.nextInt(6)+1;
        diceResults[0] = rollOne;
        diceResults[1] = rollTwo;
        return diceResults;
    }
}
