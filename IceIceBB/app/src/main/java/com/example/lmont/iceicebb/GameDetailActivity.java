package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {

    IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(this);
    Game[] gameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        TextView gameName = (TextView)findViewById(R.id.gameNameDetail);
        ImageView playersIcon = (ImageView)findViewById(R.id.playersIcon);
        ImageView sfwIcon = (ImageView)findViewById(R.id.sfwIcon);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBarDetail);
        LinearLayout tagFrameDetail = (LinearLayout)findViewById(R.id.tagFrameDetail);
        TextView gameMaterials = (TextView)findViewById(R.id.gameMaterials);
        TextView gameRules = (TextView)findViewById(R.id.gameRules);
        TextView gameComments = (TextView)findViewById(R.id.gameComments);

        String name = getIntent().getStringExtra("name");
//        Float ratingFloat = Float.valueOf(getIntent().getStringExtra("rating"));

        gameName.setText(name);
        gameMaterials.setText("Required Materials: ");
        gameRules.setText("Rules: \n");
        gameComments.setText("Comments\n");
//        ratingBar.setRating(ratingFloat);

    }
}
