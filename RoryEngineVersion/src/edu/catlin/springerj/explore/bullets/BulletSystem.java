package edu.catlin.springerj.explore.bullets;

import components.PositionComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.explore.planets.Planet;
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

        Vector2 line = vel.velocity.setLength(20);
        Graphics.drawLine(pos.position.x, pos.position.y, pos.position.x + line.x, pos.position.y + line.y, bc.color);

        Planet p = Main.gameManager.getComponent(CollisionManager.class).entityPoint(pos.position, Planet.class);
        if (p != null) {
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            bc.entity.destroySelf();
            new ParticleEmitter(pos.position, vel.velocity.setLength(-50), 10, .5, bc.color, true);
        }
    }

}
