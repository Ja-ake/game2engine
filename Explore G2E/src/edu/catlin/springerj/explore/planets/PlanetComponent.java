package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4d;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class PlanetComponent extends AbstractComponent {

    public Color4d color1;
    public Color4d color2;
    public Vector2 initialPos;

    public PlanetComponent() {
        color1 = new Color4d(1, 1, 1);
        color2 = new Color4d(.1, .1, .1);
    }

    @Override
    public void initialize(AbstractEntity e) {
        initialPos = e.get(PositionComponent.class).position;
    }

}
