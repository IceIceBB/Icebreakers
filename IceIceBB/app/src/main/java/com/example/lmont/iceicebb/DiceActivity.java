package com.example.lmont.iceicebb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

//The activity where virtual dice are simulated and rolled
public class DiceActivity extends AppCompatActivity {

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
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
