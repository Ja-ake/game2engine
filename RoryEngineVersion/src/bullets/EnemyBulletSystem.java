package bullets;

import movement.PositionComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import collisions.CollisionManager;
import enemies.HealthComponent;
import player.PlayerEntity;
import engine.AbstractEntity;
import engine.AbstractSystem;
import engine.Main;

public class EnemyBulletSystem extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;
    private AbstractEntity entity;

    public EnemyBulletSystem(PositionComponent pc, VelocityComponent vc, AbstractEntity entity) {
        this.pc = pc;
        this.vc = vc;
        this.entity = entity;
    }

    public void update() {
        PlayerEntity p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pc.position, PlayerEntity.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(5);
            final SpriteComponent sc = p.getComponent(SpriteComponent.class);
            sc.setSprite("character_idle_left_red", 8);
            p.get(CircleCollisionComponent.class).applyImpulse(vc.velocity.setLength(100));
            entity.destroySelf();
//            Core.task(new Task(true) {
//                private double time = 0;
//
//                @Override
//                public void run() {
//                    time += Main.stepSize;
//                    if (time > 0.5d) {
//                        sc.setSprite("character_idle_left", 8);
//                        Core.getDefaultTaskThread().remove(this.getID());
//                    }
//                }
//            });
        }
    }
}
