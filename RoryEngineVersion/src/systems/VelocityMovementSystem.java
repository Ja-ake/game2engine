package systems;

import components.PositionComponent;
import components.VelocityComponent;
import engine.AbstractSystem;

public class VelocityMovementSystem extends AbstractSystem {

    private PositionComponent position;
    private VelocityComponent velocity;

    public VelocityMovementSystem(PositionComponent position, VelocityComponent velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public void update() {
        position.position = position.position.add(velocity.velocity);
    }

}
