package edu.catlin.springerj.explore.collisions;

import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Vector2;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager extends AbstractComponent {

    public ArrayList<CircleCollisionComponent> list = new ArrayList();

    public boolean collisionLine(Vector2 p1, Vector2 p2, String name) {
        for (CircleCollisionComponent ccc : list) {
            if (name.equals(ccc.name)) {
                if (ccc.intersects(p1, p2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<CircleCollisionComponent> collisionLine(Vector2 p1, Vector2 p2) {
        List<CircleCollisionComponent> returnval = new ArrayList<CircleCollisionComponent>();
        for (CircleCollisionComponent ccc : list) {
            if (ccc.intersects(p1, p2)) {
                returnval.add(ccc);
            }
        }

        return returnval;
    }

    public boolean collisionPoint(Vector2 point, String name) {
        for (CircleCollisionComponent ccc : list) {
            if (name.equals(ccc.name)) {
                if (ccc.contains(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    public <E extends AbstractEntity> E entityPoint(Vector2 point, Class<E> c) {
        for (CircleCollisionComponent ccc : list) {
            if (c.isInstance(ccc.entity)) {
                if (ccc.contains(point)) {
                    return (E) ccc.entity;
                }
            }
        }
        return null;
    }

}
