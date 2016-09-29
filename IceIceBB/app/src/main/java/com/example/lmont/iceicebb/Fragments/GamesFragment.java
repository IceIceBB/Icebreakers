package com.example.lmont.iceicebb.Fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lmont.iceicebb.Game;
import com.example.lmont.iceicebb.GameListRecyclerAdapter;
import com.example.lmont.iceicebb.IcebreakerDBHelper;
import com.example.lmont.iceicebb.R;
import com.example.lmont.iceicebb.TabMainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends Fragment {

    IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
    Game[] gameArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_games, container, false);

        setup(view);

    return view;
    }
    public void setup(View view) {
        gameArray = dbHelper.getGamesLike(
                TabMainActivity.query,
                TabMainActivity.tagsQuery,
                TabMainActivity.isCleanQuery,
                TabMainActivity.isByRatingQuery,
                TabMainActivity.isByAlphabetQuery);

        //gameArray = dbHelper.getAllGames();
        RecyclerView gameList = (RecyclerView)view.findViewById(R.id.gameList);
        gameList.setHasFixedSize(true);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gameList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else{
            gameList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        GameListRecyclerAdapter adapter = new GameListRecyclerAdapter(gameArray);
        gameList.setAdapter(adapter);
    }
}
