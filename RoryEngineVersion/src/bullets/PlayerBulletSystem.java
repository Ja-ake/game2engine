package bullets;

import movement.PositionComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import collisions.CollisionManager;
import enemies.Enemy;
import enemies.HealthComponent;
import enemies.Spawner;
import particles.ParticleEmitter;
import engine.AbstractSystem;
import engine.Color4d;
import engine.Main;

public class PlayerBulletSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;
    private PlayerBulletComponent pbc;

    public PlayerBulletSystem(PositionComponent pos, VelocityComponent vel, BulletComponent bc, PlayerBulletComponent pbc) {
        this.pos = pos;
        this.vel = vel;
        this.bc = bc;
        this.pbc = pbc;
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
    public void destroy() {
        super.destroy();
        if (pbc.type == 2) {
            new ParticleEmitter(pos.position, vel.velocity.setLength(-20), 100, 5, new Color4d(.1, .1, 0), true);
        }
    }

    @Override
    public void update() {
        Enemy p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Enemy.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(10);
            if (pbc.type == 3) {
                p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(500));
            }
            bc.entity.destroySelf();
        }
        Spawner s = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Spawner.class);
        if (s != null) {
            s.get(HealthComponent.class).damage(10);
            bc.entity.destroySelf();
        }
    }

}
