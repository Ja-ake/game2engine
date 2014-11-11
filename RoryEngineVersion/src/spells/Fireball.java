package spells;

import movement.PositionComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import bullets.BulletComponent;
import collisions.CircleCollisionComponent;
import engine.AbstractEntity;
import engine.Vector2;
import graphics.SpriteRenderSystem;
import movement.VelocityMovementSystem;

public class Fireball extends AbstractEntity {

    public Fireball(Vector2 position, Vector2 velocity) {
        //Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        BulletComponent bc = add(new BulletComponent(this));
        SpriteComponent sc = add(new SpriteComponent("fireball"));
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(32, false, this));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new FireballSystem(pc, vc, bc, ccc, this));
        add(new SpriteRenderSystem(pc, sc));
    }
}
