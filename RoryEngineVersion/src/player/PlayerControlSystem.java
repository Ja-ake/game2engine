package player;

import movement.PositionComponent;
import movement.RotationComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import grapple.Grapple;
import grapple.GrappleComponent;
import collisions.CircleCollisionComponent;
import enemies.HealthComponent;
import planets.Planet;
import engine.AbstractSystem;
import engine.Keys;
import engine.MouseInput;
import engine.Vector2;

import org.lwjgl.input.Keyboard;

public class PlayerControlSystem extends AbstractSystem {

    public PositionComponent pos;
    private VelocityComponent vel;
    private RotationComponent rot;
    private SpriteComponent spr;
    private CircleCollisionComponent ccc;
    private GrappleComponent gc;
    private HealthComponent hc;
    private PlayerGravityComponent pgc;
    private PlayerEntity player;

    public PlayerControlSystem(PositionComponent pos, VelocityComponent vel, RotationComponent rot, SpriteComponent spr, CircleCollisionComponent ccc, GrappleComponent gc, HealthComponent hc, PlayerGravityComponent pgc, PlayerEntity player) {
        this.pos = pos;
        this.vel = vel;
        this.rot = rot;
        this.spr = spr;
        this.ccc = ccc;
        this.gc = gc;
        this.hc = hc;
        this.pgc = pgc;
        this.player = player;
    }

    @Override
    public void update() {
        Vector2 toPlanet = pgc.pos().subtract(pos.position).normalize();
        Vector2 relativeVel = vel.velocity.subtract(pgc.vel());
        // Rotate to face planet
        rot.rot = toPlanet.direction() + Math.PI / 2;
        // If you're on a planet
        if (ccc.placeSolid(pos.position.add(toPlanet))) {
            // Left-right movement
            boolean left = Keys.isDown(Keyboard.KEY_A);
            boolean right = Keys.isDown(Keyboard.KEY_D);
            if (left && !right) {
                vel.velocity = vel.velocity.add(toPlanet.normal().multiply(-.3));
                spr.setSprite("character_walking_left", 8);
            } else if (right && !left) {
                vel.velocity = vel.velocity.add(toPlanet.normal().multiply(.3));
                spr.setSprite("character_walking_right", 8);
            } else {
                if (relativeVel.dot(toPlanet.normal()) < 0) {
                    spr.setSprite("character_idle_left", 8);
                } else if (relativeVel.dot(toPlanet.normal()) > 0) {
                    spr.setSprite("character_idle_right", 8);
                }
            }
            // Friction
            Vector2 sideVel = toPlanet.normal().multiply(relativeVel.dot(toPlanet.normal()));
            vel.velocity = vel.velocity.add(sideVel.multiply(-.05));
            // Jumping
            boolean jump = Keys.isDown(Keyboard.KEY_W) || Keys.isDown(Keyboard.KEY_SPACE);
            if (jump) {
                vel.velocity = toPlanet.normal().multiply(relativeVel.dot(toPlanet.normal())).add(toPlanet.multiply(-10));
            } else {
                // Stick to planet
                vel.velocity = vel.velocity.add(toPlanet);
            }
            // Possible destroy grapple
            if (gc.planet != null && ccc.placeTouching(pos.position.add(toPlanet), Planet.class) == gc.planet) {
                gc.destroyGrapple();
                // gc.grapple.get(SpriteComponent.class).alpha = .5;
            }
        }
        // Slight friction
        vel.velocity = vel.velocity.multiply(.999);
        // Grapple
        if (MouseInput.isPressed(1)) {
            if (gc.grapple == null) {
                Vector2 velocity = MouseInput.mousePos().subtract(pos.position).setLength(10);
                gc.grapple = new Grapple(player, velocity);
            }
        }
        // Health
        if (hc.currentHealth == 0) {
            //Restart game
        }
    }
}
