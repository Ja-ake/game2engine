package edu.catlin.springerj.explore.planets;

import components.PositionComponent;
import components.VelocityComponent;
import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;

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
        vel.velocity = vel.velocity.add(pc.initialPos.subtract(pos.position).multiply(.5 * Main.stepSize)).multiply(.999);
    }

}
