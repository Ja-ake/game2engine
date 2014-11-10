package edu.catlin.springerj.explore.spells;

import components.PositionComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.bullets.BulletComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.enemy.Spawner;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.explore.planets.Planet;
import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;
import engine.Color4d;

public class FireballSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;
    private CircleCollisionComponent ccc;
    private AbstractEntity entity;

    @Override
    public void destroy() {
        super.destroy();
        Core.getRootManager().add(new ParticleEmitter(pos.position, vel.velocity.setLength(-20), 500, 5, new Color4d(.8, .15, .05), true));
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
            bc.entity.destroySelf();
        }
        
        if (Main.gameManager.getComponent(CollisionManager.class).collisionPoint(pos.position, "Planet")) {
            Planet p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Planet.class);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(entity);
        }
        Enemy p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(20);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(entity);
        }
        Spawner s = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(40);
            Core.getRootManager().remove(entity);
        }
    }

}
