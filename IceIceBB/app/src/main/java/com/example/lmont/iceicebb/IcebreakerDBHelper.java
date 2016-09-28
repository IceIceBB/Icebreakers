package com.example.lmont.iceicebb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by lmont on 9/25/2016.
 */
public class IcebreakerDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "icebreakerDB";
    public static final int DB_VERSION = 1;
    public static final String ICEBREAKERS_TABLE_NAME = "icebreakers";
    public static final String QUESTIONS_TABLE_NAME = "questions";

    public static final String[] ICEBREAKERS_COLUMNS = new String[]{"name", "comment", "rules", "isclean", "hasDice", "hasCards", "tags", "minPlayers", "maxPlayers", "materials", "url", "rating"};
    public static final String[] QUESTIONS_COLUMNS = new String[]{"name", "text", "sfw"};

    // CREATE TABLE [TABLE NAME] ([ATTRIBUTE NAME] [ATTRIBUTE TYPE], ...)
    public static final String CREATE_ICEBREAKERS_TABLE =
            "CREATE TABLE " + ICEBREAKERS_TABLE_NAME + " (" +
            "name INTEGER," +
            "comment TEXT," +
            "rules TEXT," +
            "isclean BOOLEAN," +
            "hasDice BOOLEAN," +
            "hasCards BOOLEAN," +
            "tags TEXT," +
            "minPlayers INTEGER," +
            "maxPlayers INTEGER," +
            "materials TEXT," +
            "url TEXT," +
            "rating INTEGER" + ")";

    public static final String CREATE_QUESTIONS_TABLE =
            "CREATE TABLE " + QUESTIONS_TABLE_NAME + " (" +
            "name TEXT," +
            "text TEXT," +
            "sfw BOOLEAN" + ")";

    public static IcebreakerDBHelper instance;

    private IcebreakerDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static IcebreakerDBHelper getInstance(Context context) {
        if (instance == null)
            instance = new IcebreakerDBHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ICEBREAKERS_TABLE);
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ICEBREAKERS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Game getGameWithName(String query) {
//        Cursor cursor = getReadableDatabase().query(
//                ICEBREAKERS_TABLE_NAME,
//                ICEBREAKERS_COLUMNS,
//                "name = '" + query + "'", null, null, null, null);
//
//        if (cursor.moveToFirst() == false)
//            return null;
//
//        Game game = new Game();
//        game.name = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[0]));
//        game.comment = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[1]));
//        game.rules = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[2]));
//        game.isClean = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[3])) > 0;
//        game.hasCards = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[4])) > 0;
//        game.hasDice = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[5])) > 0;
//        game.tags = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[6]));
//        game.minPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[7]));
//        game.maxPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[8]));
//        game.materials = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[9]));
//        game.url = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[10]));
//        game.rating = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[11]));
//
//        return game;

        Game game1 = new Game();
        game1.name = "Test 1";
        game1.comment = "TEST COMMENT";
        game1.rules = "TEST RULES";
        game1.isClean = true;
        game1.hasCards = true;
        game1.hasDice = true;
        game1.tags = "TEST TAGS";
        game1.minPlayers = 2;
        game1.maxPlayers = 6;
        game1.materials = "TEST MATERIALS";
        game1.url = "redtube.com";
        game1.rating = 69;

        return game1;
    }

    public Game[] getGamesLike(String query) {
//        Cursor cursor = getReadableDatabase().query(
//                ICEBREAKERS_TABLE_NAME,
//                ICEBREAKERS_COLUMNS,
//                "name LIKE '%" + query + "%'", null, null, null, null);
//
//        Game[] games = new Game[cursor.getCount()];
//
//        for (int x=0; x<games.length; x++) {
//            cursor.moveToPosition(x);
//            games[x] = new Game();
//            games[x].name = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[0]));
//            games[x].comment = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[1]));
//            games[x].rules = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[2]));
//            games[x].isClean = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[3])) > 0;
//            games[x].hasCards = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[4])) > 0;
//            games[x].hasDice = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[5])) > 0;
//            games[x].tags = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[6]));
//            games[x].minPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[7]));
//            games[x].maxPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[8]));
//            games[x].materials = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[9]));
//            games[x].url = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[10]));
//            games[x].rating = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[11]));
//        }
//
//        return games;

        Game game1 = new Game();
        game1.name = "Test 1";
        game1.comment = "TEST COMMENT";
        game1.rules = "TEST RULES";
        game1.isClean = true;
        game1.hasCards = true;
        game1.hasDice = true;
        game1.tags = "TEST TAGS";
        game1.minPlayers = 2;
        game1.maxPlayers = 6;
        game1.materials = "TEST MATERIALS";
        game1.url = "redtube.com";
        game1.rating = 69;

        Game game2 = new Game();
        game2.name = "Test 2";
        game2.comment = "TEST COMMENT";
        game2.rules = "TEST RULES";
        game2.isClean = true;
        game2.hasCards = true;
        game2.hasDice = true;
        game2.tags = "TEST TAGS";
        game2.minPlayers = 2;
        game2.maxPlayers = 6;
        game2.materials = "TEST MATERIALS";
        game2.url = "redtube.com";
        game2.rating = 69;

        return new Game[]{game1, game2};
    }

    public void addGame(ContentValues cv) {
        getWritableDatabase().insert(ICEBREAKERS_TABLE_NAME, null, cv);
    }

    public Game[] getAllGames() {
        Cursor cursor = getReadableDatabase().query(
                ICEBREAKERS_TABLE_NAME,
                ICEBREAKERS_COLUMNS,
                null, null, null, null, null);

        Game[] games = new Game[cursor.getCount()];

        for (int x=0; x<games.length; x++) {
            cursor.moveToPosition(x);
            games[x] = new Game();
            games[x].name = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[0]));
            games[x].comment = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[1]));
            games[x].rules = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[2]));
            games[x].isClean = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[3])) > 0;
            games[x].hasCards = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[4])) > 0;
            games[x].hasDice = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[5])) > 0;
            games[x].tags = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[6]));
            games[x].minPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[7]));
            games[x].maxPlayers = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[8]));
            games[x].materials = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[9]));
            games[x].url = cursor.getString(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[10]));
            games[x].rating = cursor.getInt(cursor.getColumnIndex(ICEBREAKERS_COLUMNS[11]));
        }

        return games;
    }

    public void deleteAllGames() {
        getWritableDatabase().delete(ICEBREAKERS_TABLE_NAME,null,null);
    }

    public void addQuestion(ContentValues cv) {
        getWritableDatabase().insert(QUESTIONS_TABLE_NAME, null, cv);
    }

    public Game.Question getRandomQuestion(boolean isSFW) {
        Cursor cursor = getReadableDatabase().query(
                QUESTIONS_TABLE_NAME,
                QUESTIONS_COLUMNS,
                null, null, null, null, null);

        int randomNum = (new Random()).nextInt(cursor.getCount());
        cursor.moveToPosition(randomNum);

        Game.Question question = new Game.Question();

        question.name = cursor.getString(cursor.getColumnIndex(QUESTIONS_COLUMNS[0]));
        question.text = cursor.getString(cursor.getColumnIndex(QUESTIONS_COLUMNS[1]));
        question.sfw = cursor.getInt(cursor.getColumnIndex(QUESTIONS_COLUMNS[2])) > 0;

        return question;
    }

    public void deleteAllQuestions() {
        getWritableDatabase().delete(QUESTIONS_TABLE_NAME,null,null);
    }

    public void resetDB() {
        onUpgrade(getWritableDatabase(), 0, 0);
    }
}
