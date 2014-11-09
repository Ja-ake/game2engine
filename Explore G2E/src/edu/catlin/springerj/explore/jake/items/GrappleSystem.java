package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.explore.jake.newjake.CircleCollisionComponent;
import edu.catlin.springerj.explore.jake.newjake.CollisionManager;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class GrappleSystem extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;
    private RotationComponent rc;
    private SpriteComponent sc;
    private GrappleComponent gc;
    private CircleCollisionComponent ccc;

    @Override
    public void initialize(AbstractEntity e) {
        pc = e.get(PositionComponent.class);
        vc = e.get(VelocityComponent.class);
        rc = e.get(RotationComponent.class);
        sc = e.get(SpriteComponent.class);
        gc = e.get(GrappleComponent.class);
        ccc = e.get(CircleCollisionComponent.class);
    }

    @Override
    public void update() {
        Vector2 playerPos = gc.player.getComponent(PositionComponent.class).position;
        Vector2 toMe = pc.position.subtract(playerPos);
        //Graphics
        rc.rot = toMe.direction() - Math.PI / 2;
        Graphics.drawLine(pc.position.x, pc.position.y, playerPos.x, playerPos.y, 0, 0, 0, sc.alpha);
        //If you're active
        if (sc.alpha == 1) {
            //If you should die
            if (toMe.lengthSquared() > 100000 || Core.getRootManager().getManager(CollisionManager.class).collisionLine(playerPos, pc.position, "Planet")) {
                vc.velocity = new Vector2();
                sc.alpha = .5;
            } else {
                //If you don't have a planet
                if (gc.planet == null) {
                    //Try to find a planet
                    gc.planet = ccc.touching("Planet");
                } else {//If you have a planet
                    vc.velocity = gc.planet.vc.velocity;
                    //if (toMe.lengthSquared() > 10000) {
                        Vector2 impulse = gc.planet.pc.position.subtract(playerPos).setLength(2000 * Core.getDefaultTimer().getDeltaTime());
                        gc.planet.applyImpulse(impulse.multiply(-1));
                        gc.player.getComponent(CircleCollisionComponent.class).applyImpulse(impulse);
                    //} else {
                    //    sc.alpha = .5;
                    //}
                }
            }
        } else {
            //Inactive
            sc.alpha -= .2 * Core.getDefaultTimer().getDeltaTime();
        }
        //If you're ready to be destroyed
        if (sc.alpha < 0) {
            if (Core.getRootManager() != null) {
                Core.getRootManager().remove(gc.grapple);
            }
        }
    }

}
