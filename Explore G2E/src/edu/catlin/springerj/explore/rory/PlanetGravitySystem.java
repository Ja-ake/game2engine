package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlanetGravitySystem extends AbstractSystem {

    private PositionComponent p;
    private VelocityComponent v;
    private PlanetGravityComponent pg;

    @Override
    public void initialize(AbstractEntity e) {
        p = e.get(PositionComponent.class);
        v = e.get(VelocityComponent.class);
        pg = e.get(PlanetGravityComponent.class);
    }

    @Override
    public void update() {
        Planet nearest = Core.getRootManager().getManager(PlanetGravityManager.class).nearest(p.position);
        pg.planetPos = nearest.get(PositionComponent.class).position;
        v.velocity = v.velocity.add(pg.planetPos.subtract(p.position).setLength(10 * Core.getDefaultTimer().getDeltaTime()));
    }

}
