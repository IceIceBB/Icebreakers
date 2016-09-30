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
    ImageView diceResultTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceOne = (FrameLayout) findViewById(R.id.diceOne);
        diceResultOne = (ImageView) findViewById(R.id.diceResultOne);

        diceTwo = (FrameLayout) findViewById(R.id.diceTwo);
        diceResultTwo = (ImageView) findViewById(R.id.diceResultTwo);

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

    private void onDiceClicked() {
        int[] rollResults = rollDice();
        int dOneResult = rollResults[0];
        int dTwoResult = rollResults[1];

//        Animation bounce = AnimationUtils.loadAnimation(DiceActivity.this,R.anim.bounce);
        Animation spinAnimation = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.dice_spin);
//        diceOne.startAnimation(bounce);
//        diceTwo.startAnimation(bounce);
//        diceResultOne.startAnimation(bounce);
//        diceResultTwo.startAnimation(bounce);
        diceOne.startAnimation(spinAnimation);
        diceTwo.startAnimation(spinAnimation);
        diceResultOne.startAnimation(spinAnimation);
        diceResultTwo.startAnimation(spinAnimation);

        //Set dice results in view
        switch (dOneResult) {
            case 1:
                //set 1st dice with result 1
                diceResultOne.setBackgroundResource(R.drawable.dice_result_1);
                break;
            case 2:
                //set 1st dice with result 2
                diceResultOne.setBackgroundResource(R.drawable.dice_result_2);
                break;
            case 3:
                //set 1st dice with result 3
                diceResultOne.setBackgroundResource(R.drawable.dice_result_3);
                break;
            case 4:
                //set 1st dice with result 4
                diceResultOne.setBackgroundResource(R.drawable.dice_result_4);
                break;
            case 5:
                //set 1st dice with result 5
                diceResultOne.setBackgroundResource(R.drawable.dice_result_5);
                break;
            case 6:
                //set 1st dice with result 6
                diceResultOne.setBackgroundResource(R.drawable.dice_result_6);
                break;
            default:
                break;
        }
        switch (dTwoResult) {
            case 1:
                //set 2nd dice with result 1
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_1);
                break;
            case 2:
                //set 2nd dice with result 2
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_2);
                break;
            case 3:
                //set 2nd dice with result 3
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_3);
                break;
            case 4:
                //set 2nd dice with result 4
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_4);
                break;
            case 5:
                //set 2nd dice with result 5
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_5);
                break;
            case 6:
                //set 2nd dice with result 6
                diceResultTwo.setBackgroundResource(R.drawable.dice_result_6);
                break;
            default:
                break;
        }

//        //    //Trying another way to set the dice result view.
//    private void change() {
//        String[] imagesNames = {"dice_result_1", "dice_result_2","dice_result_3", "dice_result_4", "dice_result_5", "dice_result_6"};
//
//        diceResultOne.setBackgroundResource(imagesNames[dOneResult]);
//        diceResultTwo.setBackgroundResource(imagesNames[dTwoResult]);
//
//    }
    }
}

