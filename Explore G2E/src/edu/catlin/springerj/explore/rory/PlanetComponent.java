package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4d;

public class PlanetComponent extends AbstractComponent {

    public Color4d color1;
    public Color4d color2;

    public PlanetComponent() {
        color1 = new Color4d(1, 1, 1);
        color2 = new Color4d(0, 0, 0);
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
