package com.example.lmont.iceicebb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView playerCount = (TextView)findViewById(R.id.playerCount);
        ImageView sfwIcon = (ImageView)findViewById(R.id.sfwIcon);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBarDetail);
        TextView gameMaterials = (TextView)findViewById(R.id.gameMaterials);
        TextView gameRules = (TextView)findViewById(R.id.gameRules);
        TextView gameComments = (TextView)findViewById(R.id.gameComments);
        Button diceRollerButton = (Button)findViewById(R.id.diceRollerButton);
        final Button cardDeckButton = (Button)findViewById(R.id.cardFlipperButton);

        final LinearLayout tagHolder = (LinearLayout) findViewById(R.id.tagFrameDetail);
        ImageView drinkIcon = (ImageView) findViewById(R.id.drinkIcon);
        ImageView movingIcon = (ImageView) findViewById(R.id.movingIcon);
        ImageView carIcon = (ImageView) findViewById(R.id.carIcon);
        final ImageView paperIcon = (ImageView) findViewById(R.id.paperIcon);


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
//ONCLICKS to send user to appropriate tool activity
        diceRollerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiceActivity.class);
                startActivity(intent);
            }
        });

        cardDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CardsActivity.class);
                startActivity(intent);
            }
        });
//IF elses (there's probably a DRYer way) to set SFW icon + visibility of tags
        if (game.isClean){
            sfwIcon.setImageDrawable(getResources().getDrawable(R.drawable.angel));
        }
        else {
            sfwIcon.setImageDrawable(getResources().getDrawable(R.drawable.devil));
        }
//TODO: check what tags are present in GAME object and toggle visibility of appropriate tag icons
        if (game.tags.contains("drinking")){
            drinkIcon.setVisibility(View.VISIBLE);
        }
        if (game.tags.contains("movement")){
            movingIcon.setVisibility(View.VISIBLE);
        }
        if (game.tags.contains("car")){
            carIcon.setVisibility(View.VISIBLE);
        }
        if (game.tags.contains("writing")){
            paperIcon.setVisibility(View.VISIBLE);
        }

        tagHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.drinkIcon:
                        Toast drinkToast = Toast.makeText(getApplicationContext(), "This game works well with booze", Toast.LENGTH_SHORT);
                        drinkToast.show();
                        break;
                    case R.id.movingIcon:
                        Toast movingToast = Toast.makeText(getApplicationContext(), "This game requires a bit of physical activity", Toast.LENGTH_SHORT);
                        movingToast.show();
                        break;
                    case R.id.carIcon:
                        Toast carToast = Toast.makeText(getApplicationContext(), "This is an ideal game to play in a car", Toast.LENGTH_SHORT);
                        carToast.show();
                        break;
                    case R.id.paperIcon:
                        Toast paperToast = Toast.makeText(getApplicationContext(), "This game requires a pen and paper", Toast.LENGTH_SHORT);
                        paperToast.show();
                        break;
                }
            }
        });

    }
}
