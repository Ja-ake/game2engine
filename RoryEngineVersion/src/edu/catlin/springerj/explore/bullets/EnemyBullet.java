package edu.catlin.springerj.explore.bullets;

import components.PositionComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.HealthComponent;
import edu.catlin.springerj.explore.player.PlayerEntity;
import engine.AbstractEntity;
import engine.Main;
import engine.Vector2;
import systems.VelocityMovementSystem;

public class EnemyBullet extends AbstractEntity {

    public EnemyBullet(Vector2 position, Vector2 velocity) {
        //Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        BulletComponent bc = add(new BulletComponent(this));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new BulletSystem(pc, vc, bc));
    }

    public void update() {
        PlayerEntity p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(getComponent(PositionComponent.class).position, PlayerEntity.class);
        if (p != null) {
            p.get(HealthComponent.class).damage(5);
            final SpriteComponent sc = p.getComponent(SpriteComponent.class);
            sc.setSprite("character_idle_left_red", 8);
            p.get(CircleCollisionComponent.class).applyImpulse(get(VelocityComponent.class).velocity.setLength(1000));
            destroySelf();
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
