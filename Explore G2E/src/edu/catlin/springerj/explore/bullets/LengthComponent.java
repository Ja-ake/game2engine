package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class LengthComponent extends AbstractComponent {

    public double length;
    public Vector2 start;

    public LengthComponent() {
        length = 0;
        start = new Vector2();
    }

    public LengthComponent(Vector2 s) {
        start = s;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
