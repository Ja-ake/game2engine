package movement;

import engine.AbstractSystem;

public class RotationSystem extends AbstractSystem {

    private RotationComponent rotation;

    public RotationSystem(RotationComponent rotation) {
        this.rotation = rotation;
    }

    @Override
    public void update() {
        rotation.rot += rotation.aVel;
    }

}
