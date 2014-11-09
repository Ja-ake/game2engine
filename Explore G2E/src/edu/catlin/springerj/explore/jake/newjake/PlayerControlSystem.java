package edu.catlin.springerj.explore.jake.newjake;

import edu.catlin.springerj.explore.planets.PlanetGravityComponent;
import edu.catlin.springerj.explore.Keys;
import edu.catlin.springerj.explore.MouseInput;
import edu.catlin.springerj.explore.jake.items.Bullet;
import edu.catlin.springerj.explore.jake.items.Grapple;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.MouseEvent;
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
    private CircleCollisionComponent ccc;
    private PlayerEntity player;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        rot = e.get(RotationComponent.class);
        spr = e.get(SpriteComponent.class);
        pg = e.get(PlanetGravityComponent.class);
        ccc = e.get(CircleCollisionComponent.class);
        player = (PlayerEntity) e;
    }

    @Override
    public void update() {
        Vector2 toPlanet = pg.planet.get(PositionComponent.class).position.subtract(pos.position).normalize();
        Vector2 relativeVel = vel.velocity.subtract(pg.planet.get(VelocityComponent.class).velocity);
        //Rotate to face planet
        rot.rot = toPlanet.direction() + Math.PI / 2;
        //If you're on a planet
        if (ccc.placeSolid(pos.position.add(toPlanet))) {
            //Left-right movement
            boolean left = Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_A);
            boolean right = Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_D);
            if (left && !right) {
                vel.velocity = vel.velocity.add(toPlanet.normal().multiply(-.4));
                spr.setSprite("character_walking_left", 8);
            } else if (right && !left) {
                vel.velocity = vel.velocity.add(toPlanet.normal().multiply(.4));
                spr.setSprite("character_walking_right", 8);
            } else {
                if (relativeVel.dot(toPlanet.normal()) < 0) {
                    spr.setSprite("character_idle_left", 8);
                } else if (relativeVel.dot(toPlanet.normal()) > 0) {
                    spr.setSprite("character_idle_right", 8);
                }
            }
            //Friction
            Vector2 sideVel = toPlanet.normal().multiply(relativeVel.dot(toPlanet.normal()));
            vel.velocity = vel.velocity.add(sideVel.multiply(-.004));
            //Jumping
            boolean jump = Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_W) || Core.getRootManager().getManager(Keys.class).isDown(Keyboard.KEY_SPACE);
            if (jump) {
                vel.velocity = toPlanet.normal().multiply(relativeVel.dot(toPlanet.normal())).add(toPlanet.multiply(-100));
            } else {
                //Stick to planet
                vel.velocity = vel.velocity.add(toPlanet);
            }
        }
        //Slight friction
        vel.velocity = vel.velocity.multiply(.9999);
        //Grapple
        if (Core.getRootManager().getManager(MouseInput.class).isReleased(MouseEvent.BUTTON_MB2)) {
            Vector2 velocity = Core.getRootManager().getManager(MouseInput.class).mousePos.subtract(pos.position).setLength(100);
            Core.getRootManager().add(new Grapple(player, velocity));
        }
        //Shooting
        if (Core.getRootManager().getManager(MouseInput.class).isReleased(MouseEvent.BUTTON_MB1)) {
            Vector2 velocity = Core.getRootManager().getManager(MouseInput.class).mousePos.subtract(pos.position).setLength(300);
            Core.getRootManager().add(new Bullet(pos.position, velocity));
        }
    }
}
