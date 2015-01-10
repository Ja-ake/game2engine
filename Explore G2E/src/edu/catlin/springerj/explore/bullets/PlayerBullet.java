package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.VelocityComponent;
import edu.catlin.springerj.g2e.physics.VelocitySystem;

public class PlayerBullet extends AbstractEntity {

    public PlayerBullet(Vector2 position, Vector2 velocity, int type) {
        //Components
        add(new PositionComponent(position));
        add(new VelocityComponent(velocity));
        add(new BulletComponent());
        add(new PlayerBulletComponent(type));
        //Systems
        add(new VelocitySystem());
        add(new BulletSystem());
        add(new PlayerBulletSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }
}
