package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlanetGravitySystem extends AbstractSystem {

    private PositionComponent pos;
    private VelocityComponent vel;
    private PlanetComponent pc;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        vel = e.get(VelocityComponent.class);
        pc = e.get(PlanetComponent.class);
    }

    @Override
    public void update() {
        vel.velocity = vel.velocity.add(pc.initialPos.subtract(pos.position).multiply(.5 * Core.getDefaultTimer().getDeltaTime())).multiply(.999);
    }

}
