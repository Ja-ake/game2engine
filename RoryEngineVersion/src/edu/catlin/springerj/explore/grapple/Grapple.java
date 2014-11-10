package edu.catlin.springerj.explore.grapple;

import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.player.PlayerEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;
import engine.AbstractEntity;
import engine.Vector2;

public class Grapple extends AbstractEntity {

    public Grapple(PlayerEntity player, Vector2 velocity) {
        //Components
        add(new PositionComponent(player.getComponent(PositionComponent.class).position));
        add(new VelocityComponent(velocity));
        add(new RotationComponent());
        add(new SpriteComponent("grapplehead"));
        add(player.get(GrappleComponent.class));
        add(new CircleCollisionComponent(8, false));
        //Systems
        add(new SpriteRenderSystem());
        add(new VelocityMovementSystem());
        add(new GrappleSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }
}
