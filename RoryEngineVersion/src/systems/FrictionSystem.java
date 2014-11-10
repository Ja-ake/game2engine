package systems;

import components.RotationComponent;
import components.VelocityComponent;
import engine.AbstractSystem;
import engine.Util;
import engine.Vector2;

public class FrictionSystem extends AbstractSystem {

    private VelocityComponent velocity;
    private RotationComponent rotation;

    public FrictionSystem(VelocityComponent velocity) {
        this(velocity, new RotationComponent());
    }

    public FrictionSystem(VelocityComponent velocity, RotationComponent rotation) {
        this.velocity = velocity;
        this.rotation = rotation;
    }

    @Override
    public void update() {
        velocity.velocity = velocity.velocity.multiply(.99);
        if (velocity.velocity.lengthSquared() < .005 && velocity.velocity.lengthSquared() > -.005) {
            velocity.velocity = new Vector2();
        }
        rotation.aVel = rotation.aVel * .99;
    }
}
