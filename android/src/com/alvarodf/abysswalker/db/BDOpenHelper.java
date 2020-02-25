package com.alvarodf.abysswalker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @since February 25th, 2020
 */
public class BDOpenHelper extends SQLiteOpenHelper {

    /**
     *
     * @param context
     * @param version
     * @since February 25th, 2020
     */
    public BDOpenHelper(Context context, int version) { super(context, "abysswalker", null, version); }

    /**
     *
     * @param db
     * @since February 25th, 2020
     */
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL("create table vanyr(hp int(2), dmg int(2), arm int(2), exp int(4), lvl int(1));"); }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     * @since February 25th, 2020
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}