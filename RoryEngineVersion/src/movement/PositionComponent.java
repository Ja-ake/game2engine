package movement;

import engine.AbstractComponent;
import engine.Vector2;

public class PositionComponent extends AbstractComponent {

    public Vector2 position;

    public PositionComponent() {
        this(new Vector2());
    }

    public PositionComponent(Vector2 pos) {
        this.position = pos;
    }

}
