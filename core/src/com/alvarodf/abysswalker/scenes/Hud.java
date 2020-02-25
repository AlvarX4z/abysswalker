package com.alvarodf.abysswalker.scenes;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the Head-Up Display (HUD) for the game. It holds information such the Labels for Hit Points, Level, etc.
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public final class Hud implements Disposable {

    private Viewport viewport; // Viewport to be displayed

    private int hp; // Vanyr's Hit Points (Life)
    private int damage; // Vanyr's Damage
    private int armor; // Vanyr's Armor (Defence)
    private static int exp; // Vanyr's Experience Points (XP)
    private int level; // Vanyr's current Level

    private Label hpLabel; // Hit Points Label
    private Label damageLabel; // Damage Label
    private Label armorLabel; // Armor Label
    private static Label expLabel; // Experience Points Label
    private Label levelLabel; // Level Label

    public Stage stage; // Stage where to add these labels

    /**
     * HUD's constructor.
     * @param b The SpriteBach where to allocate all the HUD sprites to be rendered.
     * @since January 21st, 2020
     */
    public Hud(SpriteBatch b) {

        hp = 10;
        damage = 5;
        armor = 3;
        exp = 0;
        level = 1;

        viewport = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, b);

        Table table = new Table(); // Table for organizing the Labels by a stated patron
        table.top();
        table.setFillParent(true);

        hpLabel = new Label("HP - " + hp, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        damageLabel = new Label("DMG - " + damage, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        armorLabel = new Label("ARM - " + armor, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        expLabel = new Label("EXP - " + exp, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("LVL - " + level, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(hpLabel).expandX().padTop(5); // Label's top padding sample
        table.add(damageLabel).expandX().padTop(5);
        table.add(armorLabel).expandX().padTop(5);
        table.add(expLabel).expandX().padTop(5);
        table.add(levelLabel).expandX().padTop(5);

        stage.addActor(table); // Adding the filled table to the stage

    }

    /**
     *
     * @param dt
     * @since February 25th, 2020
     */
    public void update(float dt) {

        if (level == 2) {

            hp = 12;
            damage = 7;
            armor = 5;
            exp = 0;

            hpLabel.setText("HP - " + hp);
            damageLabel.setText("DMG - " + damage);
            armorLabel.setText("ARM - " + armor);
            expLabel.setText("EXP - " + exp);
            levelLabel.setText("LVL - " + level);

        } else if (level == 3) {

            hp = 15;
            damage = 10;
            armor = 8;
            exp = 0;

            hpLabel.setText("HP - " + hp);
            damageLabel.setText("DMG - " + damage);
            armorLabel.setText("ARM - " + armor);
            expLabel.setText("EXP - " + exp);
            levelLabel.setText("LVL - " + level);

        } else if (level == 4) {

            hp = 19;
            damage = 14;
            armor = 12;
            exp = 0;

            hpLabel.setText("HP - " + hp);
            damageLabel.setText("DMG - " + damage);
            armorLabel.setText("ARM - " + armor);
            expLabel.setText("EXP - " + exp);
            levelLabel.setText("LVL - " + level);

        } else if (level == 5) {

            hp = 24;
            damage = 19;
            armor = 17;
            exp = 0;

            hpLabel.setText("HP - " + hp);
            damageLabel.setText("DMG - " + damage);
            armorLabel.setText("ARM - " + armor);
            expLabel.setText("EXP - " + exp);
            levelLabel.setText("LVL - " + level);

        }

    }

    /**
     *
     * @param value
     * @since February 25th, 2020
     */
    public static void addEXP(int value) {

        exp += value;
        expLabel.setText("EXP - " + exp);

    }

    /**
     *
     * @since January 21st, 2020
     */
    @Override
    public void dispose() { stage.dispose(); }

}
