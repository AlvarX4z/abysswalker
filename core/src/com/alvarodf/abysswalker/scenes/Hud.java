package com.alvarodf.abysswalker.scenes;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the Head-Up Display (HUD) for the game. It holds information such the Labels for Hit Points, Level, etc.
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public class Hud {

    private Viewport viewport; // Viewport to be displayed

    // MIRAR PARA COGERLOS DESDE VANYR

    private Integer hp; // VanyrSample's Hit Points (Life)
    private Integer damage; // VanyrSample's Damage
    private Integer armor; // VanyrSample's Armor (Defence)
    private Integer exp; // VanyrSample's Experience Points (XP)
    private Integer level; // VanyrSample's current Level

    private Label hpLabel; // Hit Points Label
    private Label damageLabel; // Damage Label
    private Label armorLabel; // Armor Label
    private Label expLabel; // Experience Points Label
    private Label levelLabel; // Level Label

    public Stage stage; // Stage where to add these labels

    /**
     * HUD's constructor.
     * @param b The SpriteBach where to allocate all the HUD sprites to be rendered.
     * @since January 21st, 2020
     */
    public Hud(SpriteBatch b) {

        hp = 10; // Sample values
        damage = 5;
        armor = 3;
        exp = 0;
        level = 1;

        viewport = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, b);

        Table table = new Table(); // Table for organizing the Labels by a stated patron
        table.top();
        table.setFillParent(true);

        hpLabel = new Label("HP - " + hp, new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // Label's content sample
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

}
