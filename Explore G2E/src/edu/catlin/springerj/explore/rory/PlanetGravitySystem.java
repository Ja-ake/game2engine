package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlanetGravitySystem extends AbstractSystem {

    private PositionComponent p;
    private VelocityComponent v;

    @Override
    public void initialize(AbstractEntity e) {
        p = e.get(PositionComponent.class);
        v = e.get(VelocityComponent.class);
    }

    @Override
    public void update() {
        Planet nearest = Core.getRootManager().getManager(PlanetGravityManager.class).nearest(p.position);
        System.out.println(nearest);
        v.velocity = v.velocity.add(nearest.get(PositionComponent.class).position.subtract(p.position).setLength(10 * Core.getDefaultTimer().getDeltaTime()));
    }

}
