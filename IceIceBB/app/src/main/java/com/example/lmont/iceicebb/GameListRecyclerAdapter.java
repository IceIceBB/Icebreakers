package com.example.lmont.iceicebb;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by klaus_000 on 9/25/2016.
 */

public class GameListRecyclerAdapter extends RecyclerView.Adapter<GameListRecyclerAdapter.GameListViewHolder> {

    Game[] gameArray;
    String gameRules;

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_list, parent, false);
        GameListViewHolder holder = new GameListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {
        TextView nameTextView = holder.gameName;
        RatingBar ratingBar = holder.ratingBar;
        float rating = (float) (gameArray[position].rating)/2;
        nameTextView.setText(gameArray[position].name);


//        ratingTextView.setText(gameArray[position].materials);
        ratingBar.setRating(rating);



        if (gameArray[position].tags.contains("drinking")){
           holder.drinkIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("movement")){
            holder.movingIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("car")){
            holder.carIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("writing")){
            holder.paperIcon.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return gameArray.length;
    }

    public static class GameListViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView gameName;
        RatingBar ratingBar;
        ImageView drinkIcon;
        ImageView movingIcon;
        ImageView carIcon;
        ImageView paperIcon;



        public GameListViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.gameView);
            gameName = (TextView)itemView.findViewById(R.id.gameName);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);

            drinkIcon = (ImageView) itemView.findViewById(R.id.drinkIconList);
            movingIcon = (ImageView) itemView.findViewById(R.id.movingIconList);
            carIcon = (ImageView) itemView.findViewById(R.id.carIconList);
            paperIcon = (ImageView) itemView.findViewById(R.id.paperIconList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
                    intent.putExtra("name", gameName.getText());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    public GameListRecyclerAdapter(Game[] gameArray){
        this.gameArray = gameArray;
    }
}