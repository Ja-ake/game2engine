package bullets;

import movement.PositionComponent;
import movement.VelocityComponent;
import engine.AbstractEntity;
import engine.Vector2;
import movement.VelocityMovementSystem;

public class EnemyBullet extends AbstractEntity {

    public EnemyBullet(Vector2 position, Vector2 velocity) {
        //Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent(velocity));
        BulletComponent bc = add(new BulletComponent(this));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new BulletSystem(pc, vc, bc));
        add(new EnemyBulletSystem(pc, vc, this));
    }
}
