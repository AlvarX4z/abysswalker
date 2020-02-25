package com.alvarodf.abysswalker.db;

/**
 * @since February 25th, 2020
 */
public interface DataBase {

    public int[] loadInfo();
    public void saveInfo(int hp, int dmg, int arm, int exp, int lvl);

}
