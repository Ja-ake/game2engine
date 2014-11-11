package bullets;

import movement.PositionComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import collisions.CollisionManager;
import particles.ParticleEmitter;
import planets.Planet;
import engine.AbstractSystem;
import engine.Main;
import engine.Vector2;
import graphics.Graphics;

public class BulletSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;

    public BulletSystem(PositionComponent pos, VelocityComponent vel, BulletComponent bc) {
        this.pos = pos;
        this.vel = vel;
        this.bc = bc;
    }

    @Override
    public void update() {
        if (pos.position.subtract(bc.start).lengthSquared() > bc.range * bc.range) {
            bc.entity.destroySelf();
        }

        Graphics.drawLine(pos.position.x, pos.position.y, pos.position.x - vel.velocity.x, pos.position.y - vel.velocity.y, bc.color);

        Planet p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Planet.class);
        if (p != null) {
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            bc.entity.destroySelf();
            new ParticleEmitter(pos.position, vel.velocity.setLength(-2), 10, 30, bc.color, false);
        }
    }

}
