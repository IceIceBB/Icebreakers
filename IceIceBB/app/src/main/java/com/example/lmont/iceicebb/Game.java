package com.example.lmont.iceicebb;


public class Game {
    // To create a new game, for now, just use postman to do a post to
    // https://floating-island-55807.herokuapp.com/games

    public String name;
    public String comment;
    public String rules;
    public boolean isclean;
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
                ", isclean=" + isclean +
                ", hasCards=" + hasCards +
                ", hasDice=" + hasDice +
                ", tags='" + tags + '\'' +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", materials='" + materials + '\'' +
                ", rating=" + rating +
                '}';
    }

    /**
     * Created by lmont on 9/25/2016.
     */
    public static class Question {
        // To create a new question, for now, just use postman to do a post to
        // https://floating-island-55807.herokuapp.com/questions

        public String name;
        public String text;
        public boolean sfw;

        @Override
        public String toString() {
            return "Question{" +
                    "name='" + name + '\'' +
                    ", text='" + text + '\'' +
                    ", sfw=" + sfw +
                    '}';
        }
    }

    public static class Comment {
        public String gameName;
        public String userName;
        public String text;
        public int rating;

        @Override
        public String toString() {
            return "" +
                    "'" + userName + "'\n" +
                    "'" + text + "'\n" +
                    "rating = " + rating;
        }
    }
}
