package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {

    IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(this);
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        String name = getIntent().getStringExtra("name");
        game = dbHelper.getGameWithName(name);

        TextView gameName = (TextView)findViewById(R.id.gameNameDetail);
        ImageView playersIcon = (ImageView)findViewById(R.id.playersIcon);
        TextView playerCount = (TextView)findViewById(R.id.playerCount);
        ImageView sfwIcon = (ImageView)findViewById(R.id.sfwIcon);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBarDetail);
        LinearLayout tagFrameDetail = (LinearLayout)findViewById(R.id.tagFrameDetail);
        TextView gameMaterials = (TextView)findViewById(R.id.gameMaterials);
        TextView gameRules = (TextView)findViewById(R.id.gameRules);
        TextView gameComments = (TextView)findViewById(R.id.gameComments);
        Button diceRollerButton = (Button)findViewById(R.id.diceRollerButton);
        Button cardDeckButton = (Button)findViewById(R.id.cardFlipperButton);



//        Float ratingFloat = Float.valueOf(getIntent().getStringExtra("rating"));

        gameName.setText(game.name);
        gameMaterials.setText("Required Materials: "+game.materials);
        gameRules.setText("Rules: \n"+game.rules);
        gameComments.setText("Comments\n"+game.comment);
        ratingBar.setRating(game.rating);
        playerCount.setText(game.minPlayers);
//        ratingBar.setRating(ratingFloat);

        if (!game.hasDice){
            diceRollerButton.setVisibility(View.GONE);
        }
        if (!game.hasCards){
            cardDeckButton.setVisibility(View.GONE);
        }

        if (game.isClean){
            sfwIcon.setImageDrawable(getResources().getDrawable(R.drawable.angel));
        }
        else {
            sfwIcon.setImageDrawable(getResources().getDrawable(R.drawable.devil));
        }

    }
}
