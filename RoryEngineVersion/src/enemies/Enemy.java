package enemies;

import movement.PositionComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import bullets.BulletCooldownComponent;
import bullets.BulletCooldownSystem;
import collisions.CircleCollisionComponent;
import collisions.CircleCollisionSystem;
import engine.AbstractEntity;
import engine.Vector2;
import graphics.SpriteRenderSystem;
import movement.VelocityMovementSystem;

public class Enemy extends AbstractEntity {

    public Enemy(Vector2 position) {
        // Components
        PositionComponent pc = add(new PositionComponent(position));
        VelocityComponent vc = add(new VelocityComponent());
        SpriteComponent sc = add(new SpriteComponent("enemy_smoking_black", 5, 2));
        BulletCooldownComponent bcc = add(new BulletCooldownComponent(1));
        HealthComponent hc = add(new HealthComponent(50));
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(10, true, this));
        //Systems
        add(new SpriteRenderSystem(pc, sc));
        add(new VelocityMovementSystem(pc, vc));
        add(new Enemy1System(pc, vc, bcc));
        add(new DeathSystem(this, hc));
        add(new BulletCooldownSystem(bcc));
        add(new CircleCollisionSystem(ccc));
        add(new HealthbarSystem(pc, hc));
    }
}
