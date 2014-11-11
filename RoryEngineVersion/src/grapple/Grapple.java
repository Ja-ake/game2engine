package grapple;

import movement.PositionComponent;
import movement.RotationComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import player.PlayerEntity;
import engine.AbstractEntity;
import engine.Vector2;
import graphics.SpriteRenderSystem;
import movement.VelocityMovementSystem;

public class Grapple extends AbstractEntity {

    public Grapple(PlayerEntity player, Vector2 velocity) {
        //Components
        PositionComponent pc = add(new PositionComponent(player.getComponent(PositionComponent.class).position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        RotationComponent rc = add(new RotationComponent());
        SpriteComponent sc = add(new SpriteComponent("grapplehead"));
        GrappleComponent gc = add(player.get(GrappleComponent.class));
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(8, false, this));
        //Systems
        add(new SpriteRenderSystem(pc, rc, sc));
        add(new VelocityMovementSystem(pc, vc));
        add(new GrappleSystem(pc, vc, rc, sc, gc, ccc));
    }
}
