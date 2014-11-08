package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double mass;

    public CircleCollisionComponent(double size) {
        this.size = size;
        mass = size * size;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
