package spells;

import movement.PositionComponent;
import movement.VelocityComponent;
import bullets.BulletComponent;
import collisions.CircleCollisionComponent;
import collisions.CollisionManager;
import enemies.Enemy;
import enemies.HealthComponent;
import enemies.Spawner;
import particles.ParticleEmitter;
import planets.Planet;
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

    public FireballSystem(PositionComponent pos, VelocityComponent vel, BulletComponent bc, CircleCollisionComponent ccc, AbstractEntity entity) {
        this.pos = pos;
        this.vel = vel;
        this.bc = bc;
        this.ccc = ccc;
        this.entity = entity;
    }

    @Override
    public void destroy() {
        super.destroy();
        new ParticleEmitter(pos.position, vel.velocity.setLength(-2), 500, 60, new Color4d(.8, .15, .05), true);
        for (Enemy other : ccc.touchingList(Enemy.class)) {
            other.get(HealthComponent.class).damage(10);
            other.get(CircleCollisionComponent.class).applyImpulse(other.get(PositionComponent.class).position.subtract(pos.position).setLength(500));
        }
        for (Spawner other : ccc.touchingList(Spawner.class)) {
            other.get(HealthComponent.class).damage(10);
        }
    }

    @Override
    public void update() {
        if (pos.position.subtract(bc.start).lengthSquared() > bc.range * bc.range) {
            bc.entity.destroySelf();
        }

        Planet p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Planet.class);
        if (p != null) {
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            entity.destroySelf();
        }

        Enemy e = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (e != null) {
            e.get(HealthComponent.class).damage(20);
            e.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(100));
            entity.destroySelf();
        }

        Spawner s = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(40);
            entity.destroySelf();
        }
    }

}
