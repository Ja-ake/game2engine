package systems;

import components.GravityComponent;
import components.VelocityComponent;
import engine.AbstractSystem;

public class GravitySystem extends AbstractSystem {

    private VelocityComponent velocity;
    private GravityComponent gravity;

    public GravitySystem(VelocityComponent velocity, GravityComponent gravity) {
        this.velocity = velocity;
        this.gravity = gravity;
    }

    @Override
    public void update() {
        velocity.velocity = velocity.velocity.add(gravity.g);
    }

}
