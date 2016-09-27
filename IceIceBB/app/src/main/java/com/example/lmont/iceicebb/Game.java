package com.example.lmont.iceicebb;


public class Game {
    // To create a new game, for now, just use postman to do a post to
    // https://floating-island-55807.herokuapp.com/games

    public String name;
    public String comment;
    public String rules;
    public boolean isClean;
    public boolean hasCards;
    public boolean hasDice;
    public String tags;
    public int minPlayers;
    public int maxPlayers;
    public String materials;
    public String url;
    public int rating;

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", rules='" + rules + '\'' +
                ", isClean=" + isClean +
                ", hasCards=" + hasCards +
                ", hasDice=" + hasDice +
                ", tags='" + tags + '\'' +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", materials='" + materials + '\'' +
                ", rating=" + rating +
                '}';
    }
}
