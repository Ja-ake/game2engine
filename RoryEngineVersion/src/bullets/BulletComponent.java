package bullets;

import movement.PositionComponent;
import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Color4d;
import engine.Vector2;

public class BulletComponent extends AbstractComponent {

    public double range;
    public Color4d color;
    public Vector2 start;
    public AbstractEntity entity;

    public BulletComponent(AbstractEntity entity) {
        this.entity = entity;
        range = 10000;
        color = new Color4d(1, 1, 1);
        start = entity.get(PositionComponent.class).position;
    }

}
