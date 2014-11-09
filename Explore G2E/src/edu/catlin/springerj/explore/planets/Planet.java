package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.explore.rory.CircleCollisionComponent;
import edu.catlin.springerj.explore.rory.CircleCollisionSystem;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class Planet extends AbstractEntity {

    public Planet(Vector2 pos, double size) {
        //Components
        add(new PositionComponent(pos));
        add(new VelocityComponent());
        add(new CircleCollisionComponent(size));
        add(new PlanetComponent());
        //Systems
        add(new VelocityMovementSystem());
        add(new CircleCollisionSystem());
        add(new PlanetRenderSystem());
        add(new PlanetGravitySystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
