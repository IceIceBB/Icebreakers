package com.example.lmont.iceicebb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        RecyclerView gameList = (RecyclerView) findViewById(R.id.gameList);
        gameList.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        gameList.setLayoutManager(manager);


        ArrayList games = new ArrayList<Game>();
        for (int i = 0; i < 5; i++){
            games.add(new Game(6, "testName"));
            games.add(new Game(8, "testName2"));
            games.add(new Game(4, "testName3"));
        }

        GameListRecyclerAdapter adapter = new GameListRecyclerAdapter(games);
        gameList.setAdapter(adapter);
    }
}
