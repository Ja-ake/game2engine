package enemies;

import movement.PositionComponent;
import movement.RotationComponent;
import bullets.BulletCooldownComponent;
import collisions.CollisionManager;
import planets.Planet;
import player.PlayerControlSystem;
import engine.Main;
import engine.AbstractSystem;
import engine.Vector2;
import managers.RoomComponent;
import player.PlayerEntity;

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
        Vector2 player = Main.gameManager.get(RoomComponent.class).get(PlayerEntity.class).get(PositionComponent.class).position;

        if (player.subtract(pos.position).lengthSquared() < 1000000) {
            if (Main.gameManager.getComponent(CollisionManager.class).collisionLine(pos.position, player, Planet.class) == null) {
                if (bcc.shoot()) {
                    new Enemy(pos.position.add(new Vector2(Math.random() - .5, Math.random() - .5)));
                }
            }
        }
    }

}
