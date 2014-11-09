package edu.catlin.springerj.explore.spells;

import edu.catlin.springerj.explore.bullets.BulletComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

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
