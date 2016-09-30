package com.example.lmont.iceicebb;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by lmont on 9/25/2016.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static String TAG = SyncAdapter.class.getCanonicalName();

    // Global variables
    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }


    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();

    }


    // Set to true to clear
    private static boolean first = false;
    /**
     * This is the method that contains the logic for our sync.
     * @param account
     * @param extras
     * @param authority
     * @param provider
     * @param syncResult
     */
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.d("LEO", "onPerformSync: " + Calendar.getInstance().toString());

        //Do api call to our API to get new data
        String gamesAPI = "https://floating-island-55807.herokuapp.com/games";
        String questionsAPI = "https://floating-island-55807.herokuapp.com/questions";
        String commentsAPI = "https://floating-island-55807.herokuapp.com/games/comments";
        String gamesData = "";
        String questionsData = "";
        String commentsData = "";

        HttpURLConnection gamesConnection = null;
        HttpURLConnection questionsConnection = null;
        HttpURLConnection commentsConnection = null;

        try {
            URL gamesURL = new URL(gamesAPI);
            URL questionsURL = new URL(questionsAPI);
            URL commentsURL = new URL(commentsAPI);

            gamesConnection = (HttpURLConnection) gamesURL.openConnection();
            questionsConnection = (HttpURLConnection) questionsURL.openConnection();
            commentsConnection = (HttpURLConnection) commentsURL.openConnection();

            gamesConnection.connect();
            questionsConnection.connect();
            commentsConnection.connect();

            InputStream gamesInStream = gamesConnection.getInputStream();
            InputStream questionsInStream = questionsConnection.getInputStream();
            InputStream commentsInStream = commentsConnection.getInputStream();

            gamesData = getInputData(gamesInStream);
            questionsData = getInputData(questionsInStream);
            commentsData = getInputData(commentsInStream);

            gamesConnection.disconnect();
            questionsConnection.disconnect();
            commentsConnection.disconnect();

        } catch (Exception e) {

            e.printStackTrace();
            gamesConnection.disconnect();
            questionsConnection.disconnect();
            commentsConnection.disconnect();
            return;

        }

        GamesArrayFromGson g = (new Gson()).fromJson(gamesData, GamesArrayFromGson.class);

        if (first) {
            first = false;
            mContentResolver.delete(IcebreakerContentProvider.CONTENT_URI_ICEBREAKERS, null, null);
            mContentResolver.delete(IcebreakerContentProvider.CONTENT_URI_QUESTIONS, null, null);
            mContentResolver.delete(IcebreakerContentProvider.CONTENT_URI_COMMENTS, null, null);
        }

        if (g.games.length < TabMainActivity.gamesTableSize)
            mContentResolver.delete(IcebreakerContentProvider.CONTENT_URI_ICEBREAKERS, null, null);

        for (Game game: g.games) {
            ContentValues gamesContentValues = new ContentValues();
            gamesContentValues.put("name", game.name);
            gamesContentValues.put("comment", game.comment);
            gamesContentValues.put("rules", game.rules);
            gamesContentValues.put("isclean", game.isclean);
            gamesContentValues.put("hasCards", game.hasCards);
            gamesContentValues.put("hasDice", game.hasDice);
            gamesContentValues.put("tags", game.tags);
            gamesContentValues.put("minPlayers", game.minPlayers);
            gamesContentValues.put("maxPlayers", game.maxPlayers);
            gamesContentValues.put("materials", game.materials);
            gamesContentValues.put("rating", game.rating);
            gamesContentValues.put("url", game.url);
            mContentResolver.insert(IcebreakerContentProvider.CONTENT_URI_ICEBREAKERS, gamesContentValues);
        }

        QuestionsArrayFromGson q = (new Gson()).fromJson(questionsData, QuestionsArrayFromGson.class);
        for (Game.Question question : q.questions) {
            ContentValues questionsContentValues = new ContentValues();
            questionsContentValues.put("name", question.name);
            questionsContentValues.put("text", question.text);
            questionsContentValues.put("sfw", question.sfw);
            mContentResolver.insert(IcebreakerContentProvider.CONTENT_URI_QUESTIONS, questionsContentValues);
        }

        CommentsArrayFromGson c = (new Gson()).fromJson(commentsData, CommentsArrayFromGson.class);
        for (Game.Comment comment : c.comments) {
            ContentValues commentsContentValues = new ContentValues();
            commentsContentValues.put("gameName", comment.gameName);
            commentsContentValues.put("userName", comment.userName);
            commentsContentValues.put("text", comment.text);
            commentsContentValues.put("rating", comment.rating);
            mContentResolver.insert(IcebreakerContentProvider.CONTENT_URI_COMMENTS, commentsContentValues);
        }
    }

    private class GamesArrayFromGson {
        public Game[] games;
    }

    private class QuestionsArrayFromGson {
        public Game.Question[] questions;
    }

    private class CommentsArrayFromGson {
        public Game.Comment[] comments;
    }

    /**
     * Helper method for turning an InputStream into a String.
     * @param inStream
     * @return String with the contents of the InputStream.
     * @throws IOException
     */
    private static String getInputData(InputStream inStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        String data = null;

        while ((data = reader.readLine()) != null){
            builder.append(data);
        }

        reader.close();

        return builder.toString();
    }
}