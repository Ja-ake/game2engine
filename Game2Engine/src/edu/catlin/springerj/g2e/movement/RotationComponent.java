package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class RotationComponent extends AbstractComponent {

    public double rot;

    public RotationComponent(double rot) {
        this.rot = rot;
    }

    public RotationComponent() {
        rot = 0;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
