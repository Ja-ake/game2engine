package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;

public class PlanetComponent extends AbstractComponent {

    public Color4 color1;
    public Color4 color2;
    public Vector2 initialPos;

    public PlanetComponent() {
        color1 = new Color4(1, 1, 1);
        color2 = new Color4(.1, .1, .1);
    }

    @Override
    public void initialize(AbstractEntity e) {
        initialPos = e.get(PositionComponent.class).position;
    }

}
