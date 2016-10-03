package com.example.lmont.iceicebb;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by klaus_000 on 9/25/2016.
 */

public class GameListRecyclerAdapter extends RecyclerView.Adapter<GameListRecyclerAdapter.GameListViewHolder> {


    Game[] gameArray;
    Context context;
    String gameRules;


    public GameListRecyclerAdapter(Game[] gameArray, Context context) {
        this.context = context;
        this.gameArray = gameArray;
    }

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_list, parent, false);
        GameListViewHolder holder = new GameListViewHolder(v);
        return holder;
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation anim = AnimationUtils.loadAnimation(context, R.anim.anticipateovershoot_interpolator);
        viewHolder.itemView.setAnimation(anim);
    }


    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {
        TextView nameTextView = holder.gameName;
        RatingBar ratingBar = holder.ratingBar;
        float rating = (float) (gameArray[position].rating) / 2;
        nameTextView.setText(gameArray[position].name);

        animate(holder);


//        ratingTextView.setText(gameArray[position].materials);
        ratingBar.setRating(rating);


        if (gameArray[position].tags.contains("drinking")) {
            holder.drinkIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("movement")) {
            holder.movingIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("car")) {
            holder.carIcon.setVisibility(View.VISIBLE);
        }
        if (gameArray[position].tags.contains("writing")) {
            holder.paperIcon.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return gameArray.length;
    }


    public static class GameListViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        TextView gameName;
        RatingBar ratingBar;
        ImageView drinkIcon;
        ImageView movingIcon;
        ImageView carIcon;
        ImageView paperIcon;


        public GameListViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.gameView);
            gameName = (TextView) itemView.findViewById(R.id.gameName);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

            drinkIcon = (ImageView) itemView.findViewById(R.id.drinkIconList);
            movingIcon = (ImageView) itemView.findViewById(R.id.movingIconList);
            carIcon = (ImageView) itemView.findViewById(R.id.carIconList);
            paperIcon = (ImageView) itemView.findViewById(R.id.paperIconList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View title = view.findViewById(R.id.gameName);
                    View rating = view.findViewById(R.id.ratingBar);
                    View drinkingIcon = view.findViewById(R.id.drinkIconList);
                    View movingIcon = view.findViewById(R.id.movingIconList);
                    View carIcon = view.findViewById(R.id.carIconList);
                    View paperIcon = view.findViewById(R.id.paperIconList);

                    Pair<View, String> pair1 = Pair.create(title, "titleTransition");
                    Pair<View, String> pair2 = Pair.create(rating, "ratingBarTransition");
                    Pair<View, String> pair3 = Pair.create(drinkingIcon, "drinkIconTransition");
                    Pair<View, String> pair4 = Pair.create(movingIcon, "movementIconTransition");
                    Pair<View, String> pair5 = Pair.create(carIcon, "carIconTransition");
                    Pair<View, String> pair6 = Pair.create(paperIcon, "writingIconTransition");

                    Intent intent = new Intent(view.getContext(), GameDetailActivity.class);
                    intent.putExtra("name", gameName.getText());
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), pair1, pair2, pair3, pair4, pair5, pair6);
                        view.getContext().startActivity(intent, options.toBundle());
                    } else {
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

}