package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.explore.planets.PlanetGravityComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.FrictionComponent;
import edu.catlin.springerj.g2e.movement.FrictionSystem;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class PlayerEntity extends AbstractEntity {

    public PlayerEntity(Vector2 pos) {
        //Components
        add(new PositionComponent(pos));
        add(new VelocityComponent());
        add(new RotationComponent());
        add(new SpriteComponent("sprite\\character_walking", 8));
        add(new PlanetGravityComponent());
        add(new CircleCollisionComponent(16));
        add(new FrictionComponent());
        //Systems
        add(new SpriteRenderSystem());
        add(new VelocityMovementSystem());
        add(new PlayerGravitySystem());
        add(new PlayerControlSystem());
        add(new CircleCollisionSystem());
        //add(new FrictionSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
