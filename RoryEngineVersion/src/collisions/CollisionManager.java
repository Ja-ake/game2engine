package collisions;

import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Vector2;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager extends AbstractComponent {

    public ArrayList<CircleCollisionComponent> list = new ArrayList();

    public <E extends AbstractEntity> E collisionLine(Vector2 p1, Vector2 p2, Class<E> c) {
        for (CircleCollisionComponent ccc : list) {
            if (c.isInstance(ccc.entity)) {
                if (ccc.intersects(p1, p2)) {
                    return (E) ccc.entity;
                }
            }
        }
        return null;
    }

//    public List<CircleCollisionComponent> collisionLine(Vector2 p1, Vector2 p2) {
//        List<CircleCollisionComponent> returnval = new ArrayList<CircleCollisionComponent>();
//        for (CircleCollisionComponent ccc : list) {
//            if (ccc.intersects(p1, p2)) {
//                returnval.add(ccc);
//            }
//        }
//        return returnval;
//    }

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
