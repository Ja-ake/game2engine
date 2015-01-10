package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;

import java.util.ArrayList;

public class PlanetGravityManager extends AbstractManager {

    private ArrayList<Planet> planetList = new ArrayList();

    @Override
    public AbstractManager add(AbstractEntity... es) {
    	super.add(es);
    	AbstractEntity e = es[0];
        if (e instanceof Planet) {
            planetList.add((Planet) e);
        }
        return this;
    }

    @Override
    public void initialize() {
    }

    public Planet nearest(Vector2 pos) {
        Planet min = null;
        for (Planet p : planetList) {
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

    @Override
    public void update() {
    }

}
