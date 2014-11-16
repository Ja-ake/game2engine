package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.enemy.Spawner;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlayerBulletSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;
    private PlayerBulletComponent pbc;

    public void destroy() {
        if (pbc.type == 2) {
            Core.getRootManager().add(new ParticleEmitter(pos.position, vel.velocity.setLength(-20), 100, 5, new Color4(.1, .1, 0), true));
        }
    }

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        bc = e.get(BulletComponent.class);
        pbc = e.get(PlayerBulletComponent.class);
        switch (pbc.type) {
            case 0:
                bc.color = new Color4(1, .2, .2);
                break;
            case 1:
                bc.color = new Color4(.2, .5, 1);
                break;
            case 2:
                bc.color = new Color4(.6, .8, .2);
                break;
            case 3:
                bc.color = new Color4(.9, .9, 1);
                break;
        }
    }

    @Override
    public void update() {
        Enemy p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(10);
            if (pbc.type == 3) {
                p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(5000));
            }
            Core.getRootManager().remove(bc.entity);
            destroy();
        }
        Spawner s = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(10);
            Core.getRootManager().remove(bc.entity);
            destroy();
        }
    }

}
