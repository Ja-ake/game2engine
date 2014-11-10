package edu.catlin.springerj.explore.planets;

import components.PositionComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionSystem;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;
import engine.AbstractEntity;
import engine.Vector2;

public class Planet extends AbstractEntity {

    public Planet(Vector2 pos, double size) {
        //Components
        add(new PositionComponent(pos));
        add(new VelocityComponent());
        add(new CircleCollisionComponent(size, true));
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
