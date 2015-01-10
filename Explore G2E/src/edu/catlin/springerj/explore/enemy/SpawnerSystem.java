package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.player.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.RotationComponent;

public class SpawnerSystem extends AbstractSystem {

    private PositionComponent pos;
    private RotationComponent rot;
    private BulletCooldownComponent bcc;
    private HealthComponent hc;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        rot = e.get(RotationComponent.class);
        bcc = e.get(BulletCooldownComponent.class);
        hc = e.get(HealthComponent.class);
    }

    @Override
    public void update() {
        try {
    	rot.rotation += Core.getDefaultTimer().getDeltaTime() * (6 - hc.currentHealth / 100);
        Vector2 player = Core.getRootManager().getEntities(PlayerEntity.class).get(0).getComponent(PositionComponent.class).position;
        if (player.subtract(pos.position).lengthSquared() < 1000000) {
            if (!Core.getRootManager().getManager(CollisionManager.class).collisionLine(pos.position, player, "Planet")) {
                if (bcc.shoot()) {
                    Core.getRootManager().add(new Enemy(pos.position.add(new Vector2(Math.random() - .5, Math.random() - .5))));
                }
            }
        }} catch (Exception e) {
        	return;
        }
    }

}
