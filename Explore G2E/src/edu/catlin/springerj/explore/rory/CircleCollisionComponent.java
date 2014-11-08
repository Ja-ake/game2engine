package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double mass;
    public PositionComponent pc;

    public CircleCollisionComponent(double size) {
        this.size = size;
        mass = size * size;
    }

    @Override
    public void initialize(AbstractEntity e) {
        pc = e.get(PositionComponent.class);
    }

}
