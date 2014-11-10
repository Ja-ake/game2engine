package components;

import engine.AbstractComponent;
import engine.Vector2;

public class VelocityComponent extends AbstractComponent {

    public Vector2 velocity;

    public VelocityComponent(Vector2 velocity) {
        this.velocity = velocity;
    }

    public VelocityComponent() {
        velocity = new Vector2();
    }
}
