package components;

import engine.AbstractComponent;
import engine.Vector2;

public class GravityComponent extends AbstractComponent {

    public Vector2 g;

    public GravityComponent() {
        g = new Vector2(0, -.1);
    }
}
