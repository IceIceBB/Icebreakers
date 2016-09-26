package com.example.lmont.iceicebb;

/**
 * Created by klaus_000 on 9/25/2016.
 */

class Game {

    private String gameName;
    private float gameRating;

    Game(long gameRating, String gameName) {
        this.gameRating = gameRating;
        this.gameName = gameName;
    }

    String getGameName() {
        return gameName;
    }

    float getGameRating() {
        return gameRating;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameRating(float gameRating) {
        this.gameRating = gameRating;
    }
}
