package com.alvarodf.abysswalker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @since February 25th, 2020
 */
public final class AndroidDB implements DataBase {

    private BDOpenHelper openHelper;

    /**
     *
     * @param context
     * @since February 25th, 2020
     */
    public AndroidDB(Context context) { openHelper = new BDOpenHelper(context, 1); }

    /**
     *
     * @return
     * @since February 25th, 2020
     */
    @Override
    public int[] loadInfo() {

        SQLiteDatabase db;
        Cursor cursor;
        int[] datos;

        datos = new int[5];
        db = openHelper.getWritableDatabase();
        cursor = db.query("vanyr", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            datos[0] = cursor.getInt(cursor.getColumnIndex("hp"));
            datos[1] = cursor.getInt(cursor.getColumnIndex("dmg"));
            datos[2] = cursor.getInt(cursor.getColumnIndex("arm"));
            datos[3] = cursor.getInt(cursor.getColumnIndex("exp"));
            datos[4] = cursor.getInt(cursor.getColumnIndex("lvl"));
            return datos;

        } else {

            datos[0] = 10;
            datos[1] = 5;
            datos[2] = 3;
            datos[3] = 0;
            datos[4] = 1;
            return datos;

        }

    }

    /**
     *
     * @param hp
     * @param dmg
     * @param arm
     * @param exp
     * @param lvl
     * @since February 25th, 2020
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
