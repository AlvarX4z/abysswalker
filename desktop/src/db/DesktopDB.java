package db;

import com.alvarodf.abysswalker.db.DataBase;

/**
 * Represents the Desktop DataBase. Not used in this project. Implements the custom DataBase interface from the Core.
 * @since February 25th, 2020.
 * @author Alvaro de Francisco
 */
public final class DesktopDB implements DataBase {

    /**
     * Loads the user's statistics for its Vanyr character: Current health points, damage, armor, experience points and level.
     * @return An int array which holds the values in the specified order from above.
     * @since February 25th, 2020.
     */
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
    public void saveInfo(int hp, int dmg, int arm, int exp, int lvl) { }

}