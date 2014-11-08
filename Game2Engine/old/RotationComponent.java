package edu.catlin.springerj.g2e.object.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class RotationComponent extends AbstractComponent {

    public double rot;
    public double aVel;

    public RotationComponent() {
        rot = 0;
        aVel = 0;
    }

    @Override
    public void initialize(AbstractEntity e) {

    }

}
