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

        //Click on either of the dice to roll new results.
        diceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDiceClicked();
            }
        });
        diceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onDiceClicked();
            }
        });

    }

    // A method to roll 2 dice and receive both in a single Integer[]
    private int[] rollDice() {
        int[] diceResults = new int[2];
        int rollOne = random.nextInt(6) + 1;
        int rollTwo = random.nextInt(6) + 1;
        diceResults[0] = rollOne;
        diceResults[1] = rollTwo;
        return diceResults;
    }

    private void onDiceClicked(){
        int[] rollResults = rollDice();
        int dOneResult = rollResults[0];
        int dTwoResult = rollResults[1];

        Animation spinAnimation = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.dice_spin);
        diceOne.startAnimation(spinAnimation);
        diceTwo.startAnimation(spinAnimation);
        diceResultOne.startAnimation(spinAnimation);
        getDiceResultTwo.startAnimation(spinAnimation);

        //Set dice results in view
        switch (dOneResult) {
            case 1:
                //set 1st dice with result 1
                break;
            case 2:
                //set 1st dice with result 2
                break;
            case 3:
                //set 1st dice with result 3
                break;
            case 4:
                //set 1st dice with result 4
                break;
            case 5:
                //set 1st dice with result 5
                break;
            case 6:
                //set 1st dice with result 6
                break;
            default:
                break;
        }
        switch (dTwoResult) {
            case 1:
                //set 2nd dice with result 1
                break;
            case 2:
                //set 2nd dice with result 2
                break;
            case 3:
                //set 2nd dice with result 3
                break;
            case 4:
                //set 2nd dice with result 4
                break;
            case 5:
                //set 2nd dice with result 5
                break;
            case 6:
                //set 2nd dice with result 6
                break;
            default:
                break;
        }
    }
}
