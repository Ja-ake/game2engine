package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.enemy.Spawner;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class PlayerBullet extends AbstractEntity {

    public PlayerBullet(Vector2 position, Vector2 velocity) {
        //Components
        add(new PositionComponent(position));
        add(new VelocityComponent(velocity));
        add(new LengthComponent());
        //Systems
        add(new VelocityMovementSystem());
        add(new BulletSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        Enemy p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(getComponent(PositionComponent.class).position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(10);
            p.get(CircleCollisionComponent.class).applyImpulse(get(VelocityComponent.class).velocity.setLength(1000));
            Core.getRootManager().remove(this);
        }
        Spawner s = Core.getRootManager().getManager(CollisionManager.class).entityPoint(getComponent(PositionComponent.class).position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(10);
            Core.getRootManager().remove(this);
        }
    }
}
