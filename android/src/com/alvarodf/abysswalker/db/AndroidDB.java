package com.alvarodf.abysswalker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Represents the Android SQLite DataBase which will be used for storing and retrieving data from.
 * Implements the custom interface DataBase from the Core.
 * @since February 25th, 2020.
 * @author Alvaro de Francisco Sanchez
 */
public final class AndroidDB implements DataBase {

    private BDOpenHelper openHelper; // BDOpenHelper for creating the DB

    /**
     * The Android SQLite DataBase constructor.
     * @param context A Context from which will be invoked.
     * @since February 25th, 2020.
     */
    public AndroidDB(Context context) { openHelper = new BDOpenHelper(context, 1); }

    /**
     * Loads the user's statistics for its Vanyr's level character:.
     * @return The player's level.
     * @since February 25th, 2020.
     */
    @Override
    public int loadInfo() {

        SQLiteDatabase db;
        Cursor cursor;

        db = openHelper.getWritableDatabase();
        cursor = db.query("vanyrLevel", null,null,null, null,null,null);

        if (cursor.moveToFirst()) { return cursor.getInt(cursor.getColumnIndex("level")); }
        else { return 1; }

    }

    /**
     * Saves the user's statistics for its Vanyr character: Current health points, damage, armor, experience points and level.
     * @param lvl The current level points.
     * @since February 25th, 2020.
     */
    @Override
    public void saveInfo(int lvl) {

        SQLiteDatabase db;
        Cursor cursor;
        ContentValues contentVLVL;

        db = openHelper.getWritableDatabase();
        cursor = db.query("vanyrLevel", null,null,null, null,null,null);
        contentVLVL = new ContentValues();

        contentVLVL.put("level", lvl);

        if (cursor.moveToFirst()) { db.update("vanyrLevel", contentVLVL, null, null); }
        else { db.insert("vanyrLevel", null, contentVLVL); }

        cursor.close();
        db.close();

    }

}
