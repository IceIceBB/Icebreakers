package com.example.lmont.iceicebb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//The activity where dice roller and card picker tools are accessed
public class ToolsActivity extends AppCompatActivity {

    Button cardToolButton;
    Button diceToolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
    //Set button to direct to Card tool activity
        cardToolButton = (Button) findViewById(R.id.cardToolButton);
        cardToolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolsActivity.this, CardsActivity.class);
                startActivity(intent);
            }
        });
    //Set button to direct to Dice tool activity
        diceToolButton = (Button) findViewById(R.id.diceToolButton);
        diceToolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolsActivity.this, DiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
