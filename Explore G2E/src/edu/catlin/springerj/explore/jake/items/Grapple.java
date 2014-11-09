package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.explore.jake.newjake.CircleCollisionComponent;
import edu.catlin.springerj.explore.jake.newjake.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

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
