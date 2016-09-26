package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        RecyclerView gameList = (RecyclerView) findViewById(R.id.gameList);
        gameList.setHasFixedSize(true);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        gameList.setLayoutManager(manager);


        ArrayList games = new ArrayList<Game>();
        for (int i = 0; i < 5; i++){
            games.add(new Game(6, "testName1-"+i));
            games.add(new Game(8, "testName2-"+i));
            games.add(new Game(4, "testName3-"+i));
        }

        GameListRecyclerAdapter adapter = new GameListRecyclerAdapter(games);
        gameList.setAdapter(adapter);
    }
}
