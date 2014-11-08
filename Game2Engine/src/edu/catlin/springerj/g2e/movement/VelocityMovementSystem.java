package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class VelocityMovementSystem extends AbstractSystem {

    private PositionComponent pc;
    private VelocityComponent vc;

    @Override
    public void initialize(AbstractEntity e) {
        pc = e.getComponent(PositionComponent.class);
        vc = e.getComponent(VelocityComponent.class);
    }

    @Override
    public void update() {
        pc.position = pc.position.add(vc.velocity.multiply(Core.getDefaultTimer().getDeltaTime()));
    }
}
