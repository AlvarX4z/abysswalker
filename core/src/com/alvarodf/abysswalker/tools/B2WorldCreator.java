package com.alvarodf.abysswalker.tools;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Helps creating the map and the objects with collisions within it.
 * @since February 8th, 2020.
 * @author Alvaro de Francisco
 */
public final class B2WorldCreator {

    /**
     * Constructor called in PlayScreen.
     * @param screen PlayScreen.
     * @since February 8th, 2020.
     */
    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get("forest_ground").getObjects().getByType(RectangleMapObject.class)) { // Creates body for ground

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, 0);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth(), rect.getHeight() / 10);

            fixtureDef.shape = shape;

            body.createFixture(fixtureDef);


        }

    }

}
