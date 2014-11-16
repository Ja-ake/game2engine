package planets;

import engine.AbstractComponent;
import engine.Color4d;
import engine.Vector2;

public class PlanetComponent extends AbstractComponent {

    public Color4d color1;
    public Color4d color2;
    public Vector2 initialPos;

    public PlanetComponent(Vector2 initialPos) {
        color1 = new Color4d(1, 1, 1);
        color2 = new Color4d(.1, .1, .1);
        this.initialPos = initialPos;
    }

}
