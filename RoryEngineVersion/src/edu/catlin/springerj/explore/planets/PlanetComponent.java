package edu.catlin.springerj.explore.planets;

import components.PositionComponent;
import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Color4d;
import engine.Vector2;

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
