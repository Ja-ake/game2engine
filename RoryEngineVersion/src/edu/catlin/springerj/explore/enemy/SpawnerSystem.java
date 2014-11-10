package edu.catlin.springerj.explore.enemy;

import components.PositionComponent;
import components.RotationComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.player.PlayerControlSystem;
import engine.Main;
import engine.AbstractSystem;
import engine.Vector2;

public class SpawnerSystem extends AbstractSystem {

    private PositionComponent pos;
    private RotationComponent rot;
    private BulletCooldownComponent bcc;
    private HealthComponent hc;

    public SpawnerSystem(PositionComponent pos, RotationComponent rot, BulletCooldownComponent bcc, HealthComponent hc) {
        this.pos = pos;
        this.rot = rot;
        this.bcc = bcc;
        this.hc = hc;
    }

    @Override
    public void update() {
        rot.rot += Main.stepSize * (6 - hc.currentHealth / 100);
        Vector2 player = Main.get(PlayerControlSystem.class).pos.position;

        if (player.subtract(pos.position).lengthSquared() < 1000000) {
            if (!Main.gameManager.getComponent(CollisionManager.class).collisionLine(pos.position, player, "Planet")) {
                if (bcc.shoot()) {
                    new Enemy(pos.position.add(new Vector2(Math.random() - .5, Math.random() - .5)));
                }
            }
        }
    }

}
