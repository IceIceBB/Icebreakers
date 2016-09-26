package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        TextView gameName = (TextView)findViewById(R.id.gameNameDetail);
        TextView gameRating = (TextView)findViewById(R.id.gameRatingDetail);

        String name = getIntent().getStringExtra("name");
        String rating = getIntent().getStringExtra("rating");

        gameName.setText(name);
        gameRating.setText(rating);
    }
}
