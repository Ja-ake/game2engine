package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class BulletSystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private BulletComponent bc;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        bc = e.get(BulletComponent.class);
    }

    @Override
    public void update() {
        if (pos.position.subtract(bc.start).lengthSquared() > bc.range*bc.range) {
            Core.getRootManager().remove(bc.entity);
        }

        Vector2 line = vel.velocity.setLength(10.0d);
        Graphics.drawLine(pos.position.x, pos.position.y, pos.position.x + line.x, pos.position.y + line.y, bc.color);

        if (Core.getRootManager().getManager(CollisionManager.class).collisionPoint(pos.position, "Planet")) {
            Planet p = Core.getRootManager().getManager(CollisionManager.class).entityPoint(pos.position, Planet.class);
            p.get(CircleCollisionComponent.class).applyImpulse(vel.velocity.setLength(1000));
            Core.getRootManager().remove(bc.entity);
            Core.getRootManager().add(new ParticleEmitter(pos.position, vel.velocity.setLength(-50), 10, 0.8d, 1.0d, 0.0d, 0.0d, true));
        }
    }

}
