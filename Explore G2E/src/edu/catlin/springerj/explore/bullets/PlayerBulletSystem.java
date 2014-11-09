package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.enemy.Spawner;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Color4d;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlayerBulletSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;
    private PlayerBulletComponent pbc;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        bc = e.get(BulletComponent.class);
        pbc = e.get(PlayerBulletComponent.class);
        switch (pbc.type) {
            case 0:
                bc.color = new Color4d(1, .2, .2);
                break;
            case 1:
                bc.color = new Color4d(.2, .5, 1);
                break;
            case 2:
                bc.color = new Color4d(.6, .8, .2);
                break;
            case 3:
                bc.color = new Color4d(.9, .9, 1);
                break;
        }
    }

    @Override
    public void update() {
        Enemy p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(10);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(bc.entity);
        }
        Spawner s = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(10);
            Core.getRootManager().remove(bc.entity);
        }
    }

}
