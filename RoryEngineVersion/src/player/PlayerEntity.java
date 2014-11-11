package player;

import movement.PositionComponent;
import movement.RotationComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import bullets.BulletCooldownComponent;
import bullets.BulletCooldownSystem;
import grapple.GrappleComponent;
import collisions.CircleCollisionComponent;
import collisions.CircleCollisionSystem;
import enemies.HealthComponent;
import enemies.HealthbarSystem;
import engine.AbstractEntity;
import engine.Vector2;
import graphics.ViewFollowSystem;
import graphics.SpriteRenderSystem;
import movement.VelocityMovementSystem;

public class PlayerEntity extends AbstractEntity {

    public PlayerEntity(Vector2 pos) {
        //Components
        PositionComponent pc = add(new PositionComponent(pos));
        VelocityComponent vc = add(new VelocityComponent());
        RotationComponent rc = add(new RotationComponent());
        SpriteComponent sc = add(new SpriteComponent("character_idle_right", 8, 8));
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(11, true, this));
        PlayerGravityComponent pgc = add(new PlayerGravityComponent(pos));
        GrappleComponent gc = add(new GrappleComponent(this));
        HealthComponent hc = add(new HealthComponent(100));
        PlayerWeaponComponent pwc = add(new PlayerWeaponComponent());
        BulletCooldownComponent bcc = add(new BulletCooldownComponent(.2));
        //Systems
        add(new SpriteRenderSystem(pc, rc, sc));
        add(new VelocityMovementSystem(pc, vc));
        add(new PlayerGravitySystem(pc, vc, pgc, gc));
        add(new PlayerControlSystem(pc, vc, rc, sc, ccc, gc, hc, pgc, this));
        add(new CircleCollisionSystem(ccc));
        add(new PlayerWeaponSystem(pc, pwc, bcc));
        add(new BulletCooldownSystem(bcc));
        add(new HealthbarSystem(pc, hc));
        add(new ViewFollowSystem(pc));
    }
}
