package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.explore.Keys;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import org.lwjgl.input.Keyboard;

public class PlayerControlSystem extends AbstractSystem {

    public PositionComponent pos;
    public VelocityComponent vel;
    public RotationComponent rot;
    public SpriteComponent spr;
    public PlanetGravityComponent pg;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        rot = e.get(RotationComponent.class);
        spr = e.get(SpriteComponent.class);
        pg = e.get(PlanetGravityComponent.class);
    }

    @Override
    public void update() {
        //Rotate to face planet
        Vector2 toPlanet = pg.planet.get(PositionComponent.class).position.subtract(pos.position);
        rot.rot = toPlanet.direction() + Math.PI / 2;
        //Left-right movement
        boolean left = Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_A);
        boolean right = Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_D);
        if (left && !right) {
            vel.velocity = vel.velocity.add(toPlanet.normal().setLength(-.1));
        } else if (right && !left) {
            vel.velocity = vel.velocity.add(toPlanet.normal().setLength(.1));
        }
        //Friction
        Vector2 sideVel = toPlanet.normal().multiply(vel.velocity.subtract(pg.planet.get(VelocityComponent.class).velocity).dot(toPlanet.normal()) / toPlanet.lengthSquared());
        vel.velocity = vel.velocity.add(sideVel.multiply(-.002));
        //vel.velocity = vel.velocity.multiply(.999);
    }
}
