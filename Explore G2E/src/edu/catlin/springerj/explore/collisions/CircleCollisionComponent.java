package edu.catlin.springerj.explore.collisions;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double invMass;
    public boolean solid;
    public String name;
    public PositionComponent pc;
    public VelocityComponent vc;

    public CircleCollisionComponent(double size, boolean solid) {
        this.size = size;
        invMass = 1 / (size * size);
        this.solid = solid;
    }

    public void applyImpulse(Vector2 impulse) {
        vc.velocity = vc.velocity.add(impulse.multiply(invMass));
    }

    public boolean contains(Vector2 point) {
        return point.subtract(pc.position).lengthSquared() < size * size;
    }

    @Override
    public void initialize(AbstractEntity e) {
        name = e.getClass().getSimpleName();
        pc = e.get(PositionComponent.class);
        vc = e.get(VelocityComponent.class);
        //System.out.println(name);
    }

    public boolean intersects(CircleCollisionComponent other) {
        //lengthSquared is faster than length
        return (size + other.size) * (size + other.size) > pc.position.subtract(other.pc.position).lengthSquared();
    }

    public boolean intersects(Vector2 p1, Vector2 p2) {
        if (contains(p1) || contains(p2)) {
            return true;
        }
        Vector2 axis = p2.subtract(p1).normalize();
        double center = pc.position.dot(axis);
        double r1 = p1.dot(axis);
        double r2 = p2.dot(axis);
        if ((r1 > center && r2 > center) || (r1 < center && r2 < center)) {
            return false;
        }
        return size > Math.abs(pc.position.subtract(p1).dot(axis.normal()));
    }

    public boolean placeSolid(Vector2 pos) {
        Vector2 old = pc.position;
        pc.position = pos;
        for (CircleCollisionComponent other : Core.getRootManager().getManager(CollisionManager.class).list) {
            if (other != this) {
                if (other.solid) {
                    if (intersects(other)) {
                        pc.position = old;
                        return true;
                    }
                }
            }
        }
        pc.position = old;
        return false;
    }

    public CircleCollisionComponent touching(String name) {
        for (CircleCollisionComponent other : Core.getRootManager().getManager(CollisionManager.class).list) {
            if (other != this) {
                if (name.equals(other.name)) {
                    if (intersects(other)) {
                        return other;
                    }
                }
            }
        }
        return null;
    }

}
