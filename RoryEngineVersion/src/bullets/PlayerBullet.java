package bullets;

import movement.PositionComponent;
import movement.VelocityComponent;
import engine.AbstractEntity;
import engine.Vector2;
import movement.VelocityMovementSystem;

public class PlayerBullet extends AbstractEntity {

    public PlayerBullet(Vector2 position, Vector2 velocity, int type) {
        //Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        BulletComponent bc = add(new BulletComponent(this));
        PlayerBulletComponent pbc = add(new PlayerBulletComponent(type));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new BulletSystem(pc, vc, bc));
        add(new PlayerBulletSystem(pc, vc, bc, pbc));
    }
}
