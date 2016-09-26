package com.example.lmont.iceicebb;

/**
 * Created by klaus_000 on 9/25/2016.
 */

public class Game {

    String gameName;
    long gameRating;

    public Game(long gameRating, String gameName) {
        this.gameRating = gameRating;
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public long getGameRating() {
        return gameRating;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameRating(long gameRating) {
        this.gameRating = gameRating;
    }
}
