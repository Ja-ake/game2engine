package edu.catlin.springerj.explore.jake.newjake;

import edu.catlin.springerj.explore.jake.items.GrappleComponent;
import edu.catlin.springerj.explore.planets.PlanetGravityComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.FrictionComponent;
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
        add(new SpriteComponent("character_idle_right", 8));
        get(SpriteComponent.class).imageSpeed = 2;
        add(new PlanetGravityComponent());
        add(new CircleCollisionComponent(12, true));
        add(new FrictionComponent());
        add(new GrappleComponent(this, null));
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
