package player;

import movement.PositionComponent;
import movement.VelocityComponent;
import grapple.GrappleComponent;
import planets.PlanetGravityManager;
import engine.Main;
import engine.AbstractSystem;

public class PlayerGravitySystem extends AbstractSystem {

    private PositionComponent p;
    private VelocityComponent v;
    private PlayerGravityComponent pg;
    private GrappleComponent gc;

    public PlayerGravitySystem(PositionComponent p, VelocityComponent v, PlayerGravityComponent pg, GrappleComponent gc) {
        this.p = p;
        this.v = v;
        this.pg = pg;
        this.gc = gc;
    }

    @Override
    public void update() {
        if (gc.planet == null) {
            pg.planet = Main.gameManager.getComponent(PlanetGravityManager.class).nearest(p.position);
        } else {
            pg.planet = gc.planet;
        }
        v.velocity = v.velocity.add(pg.pos().subtract(p.position).setLength(.5));
    }
}
