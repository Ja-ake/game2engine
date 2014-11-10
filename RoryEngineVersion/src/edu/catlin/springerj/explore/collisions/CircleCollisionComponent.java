package edu.catlin.springerj.explore.collisions;

import components.PositionComponent;
import components.VelocityComponent;
import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Main;
import engine.Vector2;
import java.util.ArrayList;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double invMass;
    public boolean solid;
    public String name;
    public PositionComponent pc;
    public VelocityComponent vc;
    public AbstractEntity entity;

    public CircleCollisionComponent(double size, boolean solid, AbstractEntity entity) {
        this.size = size;
        invMass = 1 / (size * size);
        this.solid = solid;
        name = entity.getClass().getSimpleName();
        pc = entity.get(PositionComponent.class);
        vc = entity.get(VelocityComponent.class);
        this.entity = entity;
    }

    public void applyImpulse(Vector2 impulse) {
        vc.velocity = vc.velocity.add(impulse.multiply(invMass));
    }

    public boolean contains(Vector2 point) {
        return point.subtract(pc.position).lengthSquared() < size * size;
    }

    @Override
    public void destroy() {
        Main.gameManager.getComponent(CollisionManager.class).list.remove(this);
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
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
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
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
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

    public ArrayList<CircleCollisionComponent> touchingList(String name) {
        ArrayList<CircleCollisionComponent> r = new ArrayList();
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
            if (other != this) {
                if (name.equals(other.name)) {
                    if (intersects(other)) {
                        r.add(other);
                    }
                }
            }
        }
        return r;
    }
}
