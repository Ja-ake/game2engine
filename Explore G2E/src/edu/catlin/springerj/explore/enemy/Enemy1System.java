package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.explore.bullets.EnemyBullet;
import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.player.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class Enemy1System extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;
    private BulletCooldownComponent brc;

    @Override
    public void initialize(AbstractEntity e) {
        pc = e.get(PositionComponent.class);
        vc = e.get(VelocityComponent.class);
        brc = e.get(BulletCooldownComponent.class);
    }

    @Override
    public void update() {
        vc.velocity = new Vector2(0.0d, 32.0d * Math.sin(Core.getDefaultTimer().getCurrentTime() * 1.6d));

        Vector2 target = Core.getRootManager().getEntity(PlayerEntity.class).getComponent(PositionComponent.class).position;
        if (!Core.getRootManager().getManager(CollisionManager.class).collisionLine(pc.position, target, "Planet")) {
            if (brc.shoot()) {
                //Shoot a bullet
                Core.getRootManager().add(new EnemyBullet(pc.position, target.subtract(pc.position)
                        .add(new Vector2(Math.random() * 75 - 37.5, Math.random() * 75 - 37.5))));
            }
        }
    }

}
