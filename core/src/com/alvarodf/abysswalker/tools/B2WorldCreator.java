package com.alvarodf.abysswalker.tools;

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
 * @since February 8th, 2020
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {

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
