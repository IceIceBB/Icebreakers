package com.example.lmont.iceicebb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lmont on 9/25/2016.
 */

public class IcebreakerContentProvider extends ContentProvider {

    private IcebreakerDBHelper dbHelper;
    private static final String AUTHORITY = "com.example.lmont.iceicebb.IcebreakerContentProvider";
    public static final Uri CONTENT_URI = Uri.parse(("content://") + AUTHORITY);
    public static final Uri CONTENT_URI_ICEBREAKERS = Uri.parse(("content://") + AUTHORITY + "/" + IcebreakerDBHelper.ICEBREAKERS_TABLE_NAME);
    public static final Uri CONTENT_URI_QUESTIONS = Uri.parse(("content://") + AUTHORITY + "/" + IcebreakerDBHelper.QUESTIONS_TABLE_NAME);
    public static final int ICEBREAKERS = 1, QUESTIONS = 2;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, IcebreakerDBHelper.ICEBREAKERS_TABLE_NAME, ICEBREAKERS);
        uriMatcher.addURI(AUTHORITY, IcebreakerDBHelper.QUESTIONS_TABLE_NAME, QUESTIONS);
    }

    @Override
    public boolean onCreate() {
        dbHelper = IcebreakerDBHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ICEBREAKERS:

                break;
            case QUESTIONS:

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ICEBREAKERS:
                dbHelper.deleteAllGames();
                break;
            case QUESTIONS:
                dbHelper.deleteAllQuestions();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }


    // NOT IMPLEMENTED ----------------------------
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
    // ---------------------------------------------
}
