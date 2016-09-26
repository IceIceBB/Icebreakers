package com.example.lmont.iceicebb;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by klaus_000 on 9/25/2016.
 */

public class GameListRecyclerAdapter extends RecyclerView.Adapter<GameListRecyclerAdapter.GameListViewHolder> {

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_list, parent, false);
        GameListViewHolder holder = new GameListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GameListViewHolder holder, final int position) {
        TextView nameTextView = holder.gameName;
        TextView ratingTextView = holder.gameRating;

        nameTextView.setText(games.get(position).getGameName());
        ratingTextView.setText(String.valueOf(games.get(position).getGameRating()));
    }


    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class GameListViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView gameName;
        TextView gameRating;


        GameListViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.gameView);
            gameName = (TextView)itemView.findViewById(R.id.gameName);
            gameRating = (TextView)itemView.findViewById(R.id.gameRating);
        }
    }

    List<Game> games;

    GameListRecyclerAdapter(List<Game> games){
        this.games = games;
    }
}
