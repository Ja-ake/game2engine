package enemies;

import movement.PositionComponent;
import movement.VelocityComponent;
import bullets.EnemyBullet;
import bullets.BulletCooldownComponent;
import collisions.CollisionManager;
import planets.Planet;
import engine.Main;
import engine.AbstractSystem;
import engine.Vector2;
import managers.RoomComponent;
import player.PlayerEntity;

public class Enemy1System extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;
    private BulletCooldownComponent brc;

    public Enemy1System(PositionComponent pc, VelocityComponent vc, BulletCooldownComponent brc) {
        this.pc = pc;
        this.vc = vc;
        this.brc = brc;
    }

    @Override
    public void update() {
        Vector2 target = Main.gameManager.get(RoomComponent.class).get(PlayerEntity.class).get(PositionComponent.class).position;
        vc.velocity = vc.velocity.add(target.subtract(pc.position).setLength((Math.min(500, -100 + target.subtract(pc.position).length() / 3)) * Main.stepSize)).multiply(.99);

        if (Main.gameManager.getComponent(CollisionManager.class).collisionLine(pc.position, target, Planet.class) == null) {
            if (brc.shoot()) {
                //Shoot a bullet
                new EnemyBullet(pc.position, target.subtract(pc.position)
                        .add(new Vector2(Math.random() * 75 - 37.5, Math.random() * 75 - 37.5)).setLength(20));
            }
        }
    }

}
