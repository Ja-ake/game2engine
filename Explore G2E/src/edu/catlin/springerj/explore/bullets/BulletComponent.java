package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class BulletComponent extends AbstractComponent {

    public double range;
    public Color4 color;
    public Vector2 start;
    public AbstractEntity entity;

    public BulletComponent() {
        range = 10000;
        color = new Color4(1, 1, 1);
    }

    @Override
    public void initialize(AbstractEntity e) {
        start = e.get(PositionComponent.class).position;
        entity = e;
    }

}
