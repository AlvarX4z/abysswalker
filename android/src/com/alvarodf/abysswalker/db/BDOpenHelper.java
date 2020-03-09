package com.alvarodf.abysswalker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A custom class for managing SQLite DataBase. Extends from SQLiteOpenHelper.
 * @since February 25th, 2020.
 * @author Alvaro de Francisco Sanchez
 */
public final class BDOpenHelper extends SQLiteOpenHelper {

    /**
     * Constructor.
     * @param context Context from which will be invoked.
     * @param version Database's version. This should be 1.
     * @since February 25th, 2020.
     */
    public BDOpenHelper(Context context, int version) { super(context, "abysswalker", null, version); }

    /**
     * Mandatory function that creates the SQLite DataBase when the application is installed.
     * This database has an only table called 'vanyr' with 5 int value columns in it: hp, dmg, arm, exp, lvl.
     * @param db The DataBase.
     * @since February 25th, 2020.
     */
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL("create table vanyrLevel(lvl int(1));"); }

    /**
     * Mandatory function that updates the SQLite DataBase when the stated version is changed. This should not be called in this game.
     * @param db The DataBase.
     * @param oldVersion The previous version, 1 in this case.
     * @param newVersion The new version, 2 if there should be any changes.
     * @since February 25th, 2020.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}