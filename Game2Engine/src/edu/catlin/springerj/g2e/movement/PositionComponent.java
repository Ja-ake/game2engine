package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class PositionComponent extends AbstractComponent {

    public Vector2 position;

    public PositionComponent(Vector2 position) {
        this.position = position;
    }

    public PositionComponent() {
        this.position = new Vector2();
    }

    @Override
    public void initialize(AbstractEntity e) {

    }
}
