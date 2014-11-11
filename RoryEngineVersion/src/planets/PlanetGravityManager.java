package planets;

import movement.PositionComponent;
import collisions.CircleCollisionComponent;
import engine.AbstractComponent;
import engine.Vector2;

import java.util.ArrayList;

public class PlanetGravityManager extends AbstractComponent {

    public ArrayList<Planet> list = new ArrayList();

    public Planet nearest(Vector2 pos) {
        Planet min = null;
        for (Planet p : list) {
            if (min == null) {
                min = p;
            } else {
                double g1 = min.get(PositionComponent.class).position.subtract(pos).lengthSquared() * min.get(CircleCollisionComponent.class).invMass;
                double g2 = p.get(PositionComponent.class).position.subtract(pos).lengthSquared() * p.get(CircleCollisionComponent.class).invMass;
                if (g2 < g1) {
                    min = p;
                }
            }
        }
        return min;
    }
}
