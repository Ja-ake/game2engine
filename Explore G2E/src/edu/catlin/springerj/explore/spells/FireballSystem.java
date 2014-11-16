package edu.catlin.springerj.explore.spells;

import edu.catlin.springerj.explore.bullets.BulletComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.enemy.Spawner;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class FireballSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;
    private CircleCollisionComponent ccc;
    private AbstractEntity entity;

    public void destroy() {
        Core.getRootManager().add(new ParticleEmitter(pos.position, vel.velocity.setLength(-20), 500, 5, new Color4(.8, .15, .05), true));
        for (CircleCollisionComponent other : ccc.touchingList("Enemy")) {
            other.entity.get(HealthComponent.class).damage(10);
            other.applyImpulse(other.pc.position.subtract(pos.position).setLength(5000));
        }
        for (CircleCollisionComponent other : ccc.touchingList("Spawner")) {
            other.entity.get(HealthComponent.class).damage(10);
        }
    }

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        bc = e.get(BulletComponent.class);
        ccc = e.get(CircleCollisionComponent.class);
        entity = e;
    }

    @Override
    public void update() {
        if (pos.position.subtract(bc.start).lengthSquared() > bc.range*bc.range) {
            Core.getRootManager().remove(bc.entity);
            destroy();
        }
        
        if (Core.getRootManager().getManager(CollisionManager.class).collisionPoint(pos.position, "Planet")) {
            Planet p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Planet.class);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(entity);
            destroy();
        }
        Enemy p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(20);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(entity);
            destroy();
        }
        Spawner s = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(40);
            Core.getRootManager().remove(entity);
            destroy();
        }
    }

}
