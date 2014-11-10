package edu.catlin.springerj.explore.spells;

import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.bullets.BulletComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import engine.AbstractEntity;
import engine.Vector2;
import systems.VelocityMovementSystem;

public class Fireball extends AbstractEntity {

    public Fireball(Vector2 position, Vector2 velocity) {
        //Components
        add(new PositionComponent(position));
        add(new VelocityComponent(velocity));
        add(new BulletComponent());
        add(new SpriteComponent("fireball"));
        add(new RotationComponent());
        add(new CircleCollisionComponent(32, false));
        //Systems
        add(new VelocityMovementSystem());
        add(new FireballSystem());
        add(new SpriteRenderSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
