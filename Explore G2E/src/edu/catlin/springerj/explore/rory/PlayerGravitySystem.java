package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class PlayerGravitySystem extends AbstractSystem {

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
        pg.planet = Core.getRootManager().getManager(PlanetGravityManager.class).nearest(p.position);
        v.velocity = v.velocity.add(pg.planet.get(PositionComponent.class).position.subtract(p.position).setLength(100 * Core.getDefaultTimer().getDeltaTime()));
    }

}
