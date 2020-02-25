package db;

import com.alvarodf.abysswalker.db.DataBase;

/**
 * @since February 25th, 2020
 */
public class DesktopDB implements DataBase {

    @Override
    public int[] loadInfo() {

        int[] datos = new int[5];

        datos[0] = 10;
        datos[1] = 5;
        datos[2] = 3;
        datos[3] = 0;
        datos[4] = 1;

        return datos;

    }

    @Override
    public void saveInfo(int hp, int dmg, int arm, int exp, int lvl) { }

}