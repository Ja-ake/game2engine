package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class FrictionComponent extends AbstractComponent {

    public double friction;

    @Override
    public void initialize(AbstractEntity e) {

    }

    public FrictionComponent() {
        friction = 0.05f;
    }

    public FrictionComponent(double f) {
        friction = f;
    }
}
