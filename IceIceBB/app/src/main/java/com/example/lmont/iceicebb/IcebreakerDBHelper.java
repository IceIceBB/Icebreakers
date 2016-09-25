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
}
