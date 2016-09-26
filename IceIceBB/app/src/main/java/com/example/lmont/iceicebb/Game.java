package com.example.lmont.iceicebb;

/**
 * Created by klaus_000 on 9/25/2016.
 */

public class Game {

    String gameName;
    int gameRating;

    public Game(int gameRating, String gameName) {
        this.gameRating = gameRating;
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameRating() {
        return gameRating;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameRating(int gameRating) {
        this.gameRating = gameRating;
    }
}
