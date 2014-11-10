package components;

import engine.AbstractComponent;
import engine.Main;
import engine.Vector2;
import physics.CollisionShape;

public class PhysicsComponent extends AbstractComponent {

    public double invMass;
    public double invRotMass;
    public double restitution;
    public PositionComponent position;
    public VelocityComponent velocity;
    public RotationComponent rotation;
    public CollisionShape collisionShape;

    public PhysicsComponent(PositionComponent position, VelocityComponent velocity, RotationComponent rotation, CollisionShape collisionShape) {
        this.position = position;
        this.velocity = velocity;
        this.rotation = rotation;
        this.collisionShape = collisionShape;
        invMass = 0;
        invRotMass = 0;
        restitution = 0;
        collisionShape.setPos(position.position);
        collisionShape.setRot(rotation.rot);
        Main.gameManager.getComponent(PhysicsManagerComponent.class).physicsComponentList.add(this);
    }

    public PhysicsComponent(PositionComponent position, CollisionShape collisionShape) {
        this(position, new VelocityComponent(), new RotationComponent(), collisionShape);
    }

    public void applyImpulse(Vector2 point, Vector2 impulse) {
        velocity.velocity = velocity.velocity.add(impulse.multiply(invMass));
        rotation.aVel += invRotMass * point.subtract(position.position).cross(impulse);
    }

    @Override
    public void destroy() {
        Main.gameManager.getComponent(PhysicsManagerComponent.class).physicsComponentList.remove(this);
    }

    public Vector2 velocityAt(Vector2 point) {
        return velocity.velocity.add(point.subtract(position.position).normal().multiply(rotation.aVel));
    }

}
