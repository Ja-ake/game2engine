package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class PlanetComponent extends AbstractComponent {

    public int color1;
    public int color2;

    public PlanetComponent() {
        color1 = 0xFFFFFF;
        color2 = 0x000000;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
