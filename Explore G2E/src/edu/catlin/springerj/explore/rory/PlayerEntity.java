package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;

public class PlayerEntity extends AbstractEntity {

    public PlayerEntity(Vector2 pos) {
        //Components
        add(new PositionComponent(pos));
        add(new RotationComponent());
        add(new SpriteComponent("sprite\\block_red"));
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
