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
     * Loads the user's statistics for its Vanyr character: Current health points, damage, armor, experience points and level.
     * @return An int array which holds the values in the specified order from above.
     * @since February 25th, 2020.
     */
    @Override
    public int[] loadInfo() {

        SQLiteDatabase db;
        Cursor cursor;
        int[] datos;

        datos = new int[5];
        db = openHelper.getWritableDatabase();
        cursor = db.query("vanyr", null, null, null, null, null, null);

        if (cursor.moveToFirst()) { // Retrieves the data.

            datos[0] = cursor.getInt(cursor.getColumnIndex("hp"));
            datos[1] = cursor.getInt(cursor.getColumnIndex("dmg"));
            datos[2] = cursor.getInt(cursor.getColumnIndex("arm"));
            datos[3] = cursor.getInt(cursor.getColumnIndex("exp"));
            datos[4] = cursor.getInt(cursor.getColumnIndex("lvl"));
            return datos;

        } else { // If data isn't retrieved, returns a default set of values as a new game.

            datos[0] = 10;
            datos[1] = 5;
            datos[2] = 3;
            datos[3] = 0;
            datos[4] = 1;
            return datos;

        }

    }

    /**
     * Saves the user's statistics for its Vanyr character: Current health points, damage, armor, experience points and level.
     * @param hp The current health points.
     * @param dmg The current damage points.
     * @param arm The current armor points.
     * @param exp The current experience points.
     * @param lvl The current level points.
     * @since February 25th, 2020.
     */
    @Override
    public void saveInfo(int hp, int dmg, int arm, int exp, int lvl) {

        SQLiteDatabase db;
        Cursor cursor;
        ContentValues contentVHP;
        ContentValues contentVDMG;
        ContentValues contentVARM;
        ContentValues contentVEXP;
        ContentValues contentVLVL;

        db = openHelper.getWritableDatabase();
        cursor = db.query("vanyr", null, null, null, null, null, null);

        contentVHP = new ContentValues();
        contentVDMG = new ContentValues();
        contentVARM = new ContentValues();
        contentVEXP = new ContentValues();
        contentVLVL = new ContentValues();

        contentVHP.put("hp", hp);
        contentVDMG.put("dmg", dmg);
        contentVARM.put("arm", arm);
        contentVEXP.put("exp", exp);
        contentVLVL.put("lvl", lvl);

        if (cursor.moveToFirst()) {

            db.update("vanyr", contentVHP, null, null);
            db.update("vanyr", contentVDMG, null, null);
            db.update("vanyr", contentVARM, null, null);
            db.update("vanyr", contentVEXP, null, null);
            db.update("vanyr", contentVLVL, null, null);

        }

        cursor.close();
        db.close();

    }

}
