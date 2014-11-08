package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class PlayerEntity extends AbstractEntity {
    
    public PlayerEntity(Vector2 pos) {
        add(new PositionComponent(pos));
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
