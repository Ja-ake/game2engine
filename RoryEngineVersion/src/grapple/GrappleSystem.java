package grapple;

import movement.PositionComponent;
import movement.RotationComponent;
import graphics.SpriteComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import collisions.CollisionManager;
import planets.Planet;
import engine.Main;
import engine.AbstractSystem;
import engine.Color4d;
import engine.Vector2;
import graphics.Graphics;

public class GrappleSystem extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;
    private RotationComponent rc;
    private SpriteComponent sc;
    private GrappleComponent gc;
    private CircleCollisionComponent ccc;

    public GrappleSystem(PositionComponent pc, VelocityComponent vc, RotationComponent rc, SpriteComponent sc, GrappleComponent gc, CircleCollisionComponent ccc) {
        this.pc = pc;
        this.vc = vc;
        this.rc = rc;
        this.sc = sc;
        this.gc = gc;
        this.ccc = ccc;
    }

    @Override
    public void update() {
        Vector2 playerPos = gc.player.getComponent(PositionComponent.class).position;
        Vector2 toMe = pc.position.subtract(playerPos);
        //Graphics
        rc.rot = toMe.direction() - Math.PI / 2;
        Graphics.drawLine(pc.position.x, pc.position.y, playerPos.x, playerPos.y, Color4d.BLACK.setA(sc.color.a));
        //If you're active
        if (sc.color.a == 1) {
            //If you should die
            if (toMe.lengthSquared() > 200000 || Main.gameManager.get(CollisionManager.class).collisionLine(playerPos, playerPos.add(pc.position.subtract(playerPos).multiply(.9)), Planet.class) != null) {
                vc.velocity = new Vector2();
                sc.color = sc.color.setA(.5);
            } else {
                //If you don't have a planet
                if (gc.planet == null) {
                    //Try to find a planet
                    gc.planet = ccc.touching(Planet.class);
                } else {//If you have a planet
                    //Follow the planet
                    vc.velocity = gc.planet.getComponent(VelocityComponent.class).velocity;
                    //Pull planet and player together
                    Vector2 impulse = gc.planet.getComponent(PositionComponent.class).position.subtract(playerPos).setLength(2000 * Main.stepSize);
                    gc.planet.getComponent(CircleCollisionComponent.class).applyImpulse(impulse.multiply(-1));
                    gc.player.getComponent(CircleCollisionComponent.class).applyImpulse(impulse);
                }
            }
        } else {
            //Inactive
            sc.color = sc.color.setA(sc.color.a - 2 * Main.stepSize);
        }
        //If you're ready to be destroyed
        if (sc.color.a < 0) {
            gc.destroyGrapple();
        }
    }

}
