package planets;

import movement.PositionComponent;
import movement.VelocityComponent;
import engine.Main;
import engine.AbstractSystem;

public class PlanetGravitySystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private PlanetComponent pc;

    public PlanetGravitySystem(PositionComponent pos, VelocityComponent vel, PlanetComponent pc) {
        this.pos = pos;
        this.vel = vel;
        this.pc = pc;
    }

    @Override
    public void update() {
        vel.velocity = vel.velocity.add(pc.initialPos.subtract(pos.position).multiply(.5 * Main.stepSize)).multiply(.99);
    }

}
