package edu.catlin.springerj.explore.bullets;

import java.util.List;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
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
    }

    @Override
    public void initialize() {
        add(new VelocityMovementSystem());
    }

    @Override
    public void update() {
        if (get(LengthComponent.class).length > 1024 * 16) {
            this.getManager().remove(this);
        }

        Vector2 pos = get(PositionComponent.class).position;
        Vector2 line = get(VelocityComponent.class).velocity.setLength(10.0d);
        Graphics.drawLine(pos.x, pos.y, pos.x + line.x, pos.y + line.y, 1.0d, 0.3d, 0.3d, 1.0d);
        get(LengthComponent.class).length += get(VelocityComponent.class).velocity.multiply(Core.getDefaultTimer().getDeltaTime()).length();

        for (CircleCollisionComponent ccc : Core.getRootManager().getManager(CollisionManager.class)
                .collisionLine(pos, line)) {
            List<AbstractEntity> entities = Core.getRootManager().getEntities();
            if (ccc.name.equals("PlayerEntity")) {
                for (int i = 0; i < entities.size(); i++) {
                    AbstractEntity e;
                    if (!((e = entities.get(i)) instanceof PlayerEntity)) {
                        continue;
                    }

                    PlayerEntity p = (PlayerEntity) e;
                    final SpriteComponent sc = p
                            .getComponent(SpriteComponent.class);
                    sc.setSprite("character_idle_left_red", 8);
                    p.get(CircleCollisionComponent.class).applyImpulse(
                            get(VelocityComponent.class).velocity
                            .setLength(10000.0d));
                    Core.getRootManager().remove(this);
                    Core.task(new Task(true) {
                        private double time = 0;

                        @Override
                        public void run() {
                            time += Core.getDefaultTimer().getDeltaTime();
                            if (time > 0.5d) {
                                sc.setSprite("character_idle_left", 8);
                                Core.getDefaultTaskThread()
                                        .remove(this.getID());
                            }
                        }
                    });
                }
            }

            if (ccc.name.equals("Planet")) {
                // display particles
                Core.getRootManager().remove(this);
            }

            System.out.println(ccc.name);
        }
    }

}
