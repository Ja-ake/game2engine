package enemies;

import movement.PositionComponent;
import movement.RotationComponent;
import graphics.SpriteComponent;
import bullets.BulletCooldownComponent;
import bullets.BulletCooldownSystem;
import collisions.CircleCollisionComponent;
import engine.AbstractEntity;
import engine.Vector2;
import graphics.SpriteRenderSystem;

public class Spawner extends AbstractEntity {

    public Spawner(Vector2 position) {
        // Components
        PositionComponent pc = add(new PositionComponent(position));
        RotationComponent rc = add(new RotationComponent());
        SpriteComponent sc = add(new SpriteComponent("spawner"));
        BulletCooldownComponent bcc = add(new BulletCooldownComponent(15));
        HealthComponent hc = add(new HealthComponent(500));
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(16, true, this));
        ccc.invMass = 0;
        //Systems
        add(new SpriteRenderSystem(pc, sc));
        add(new SpawnerSystem(pc, rc, bcc, hc));
        add(new DeathSystem(this, hc));
        add(new BulletCooldownSystem(bcc));
        add(new HealthbarSystem(pc, hc));
    }
}
