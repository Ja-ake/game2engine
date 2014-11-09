package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.player.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;
import edu.catlin.springerj.g2e.thread.Task;

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
            System.out.println("hi");
            final SpriteComponent sc = p.getComponent(SpriteComponent.class);
            sc.setSprite("character_idle_left_red", 8);
            p.get(CircleCollisionComponent.class).applyImpulse(get(VelocityComponent.class).velocity.setLength(10000));
            Core.getRootManager().remove(this);
            Core.task(new Task(true) {
                private double time = 0;

                @Override
                public void run() {
                    time += Core.getDefaultTimer().getDeltaTime();
                    if (time > 0.5d) {
                        sc.setSprite("character_idle_left", 8);
                        Core.getDefaultTaskThread().remove(this.getID());
                    }
                }
            });
        }
    }
}
