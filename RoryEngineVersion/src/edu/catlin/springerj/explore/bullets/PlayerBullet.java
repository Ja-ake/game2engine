package edu.catlin.springerj.explore.bullets;

import components.PositionComponent;
import components.VelocityComponent;
import engine.AbstractEntity;
import engine.Vector2;
import systems.VelocityMovementSystem;

public class PlayerBullet extends AbstractEntity {

    public PlayerBullet(Vector2 position, Vector2 velocity, int type) {
        //Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        add(new BulletComponent());
        add(new PlayerBulletComponent(type));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new BulletSystem());
        add(new PlayerBulletSystem());
    }
}
