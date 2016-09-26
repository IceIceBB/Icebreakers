package com.example.lmont.iceicebb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lmont on 9/25/2016.
 */
public class IcebreakerDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "icebreakerDB";
    public static final int DB_VERSION = 1;
    public static final String ICEBREAKERS_TABLE_NAME = "icebreakers";
    public static final String QUESTIONS_TABLE_NAME = "questions";

    public static final String[] ICEBREAKERS_COLUMNS = new String[]{"name", "comment", "rules", "isclean", "hasDice", "hasCards", "tags", "minPlayers", "maxPlayers", "materials", "rating"};
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

    public Game getGameWithName(String gameName) {
        // Mock Data
        Game test = new Game();
        test.name = gameName;
        test.comment = "Comment";
        test.rules = "1) Rules \n2)Rules \n3)Rules";
        test.isClean = false;
        test.hasCards = true;
        test.hasDice = true;
        test.tags = "TAGS TAGS TAGS";
        test.minPlayers = 2;
        test.maxPlayers = 10;
        test.materials = "Paper, Pencil, Anal Beads";
        test.rating = 69;

        return test;
    }

    public Game[] getAllGames() {
        // Mock Data
        Game test = new Game();
        test.name = "Game1";
        test.comment = "Comment";
        test.rules = "1) Rules \n2)Rules \n3)Rules";
        test.isClean = false;
        test.hasCards = true;
        test.hasDice = true;
        test.tags = "TAGS TAGS TAGS";
        test.minPlayers = 2;
        test.maxPlayers = 10;
        test.materials = "Paper, Pencil, Anal Beads";
        test.rating = 69;

        Game test2 = new Game();
        test2.name = "Game1";
        test2.comment = "Comment";
        test2.rules = "1) Rules \n2)Rules \n3)Rules";
        test2.isClean = false;
        test2.hasCards = true;
        test2.hasDice = true;
        test2.tags = "TAGS TAGS TAGS";
        test2.minPlayers = 2;
        test2.maxPlayers = 10;
        test2.materials = "Paper, Pencil, Anal Beads";
        test2.rating = 69;

        return new Game[]{test, test2};
    }

    public void deleteAllGames() {}

    public Question getRandomQuestion(boolean isSFW) {
        Question test = new Question();
        if (isSFW) {
            test.name = "Game1";
            test.text = "Sister Margeret";
            test.sfw = true;
        } else {
            test.name = "Game2";
            test.text = "Balls";
            test.sfw = false;
        }

        return test;
    }

    public void deleteAllQuestions() {}
}
