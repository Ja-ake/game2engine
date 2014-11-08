package edu.catlin.springerj.g2e.object.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.object.movement.RotationComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityComponent;

public class PhysicsComponent extends AbstractComponent {

    public double invMass;
    public double invRotMass;
    public double restitution;
    public PositionComponent position;
    public VelocityComponent velocity;
    public RotationComponent rotation;
    public CollisionShape collisionShape;

    public PhysicsComponent(PositionComponent position, VelocityComponent velocity, RotationComponent rotation, CollisionShape collisionShape) {
        collisionShape = collisionShape;
        invMass = 0;
        invRotMass = 0;
        restitution = 0;
    }

    public PhysicsComponent(PositionComponent position, CollisionShape collisionShape) {
        this(position, new VelocityComponent(), new RotationComponent(), collisionShape);
    }

    public void applyImpulse(Vector2 point, Vector2 impulse) {
        velocity.velocity = velocity.velocity.add(impulse.multiply(invMass));
        rotation.aVel += invRotMass * point.subtract(position.pos).cross(impulse);
    }

    @Override
    public void destroy() {
        Main.gameManager.getComponent(PhysicsManagerComponent.class).physicsComponentList.remove(this);
    }
    
    public void initialize(AbstractEntity e) {
        position = e.get(PositionComponent.class);
        velocity = e.get(VelocityComponent.class);
        rotation = e.get(RotationComponent.class);
        collisionShape.setPos(position.position);
        collisionShape.setRot(rotation.);
        Main.gameManager.getComponent(PhysicsManagerComponent.class).physicsComponentList.add(this);
    }

    public Vector2 velocityAt(Vector2 point) {
        return velocity.vel.add(point.subtract(position.pos).normal().multiply(rotation.aVel));
    }

}
