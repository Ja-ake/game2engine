package collisions;

import movement.PositionComponent;
import movement.VelocityComponent;
import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Main;
import engine.Vector2;
import java.util.ArrayList;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double invMass;
    public boolean solid;
    public PositionComponent pc;
    public VelocityComponent vc;
    public AbstractEntity entity;

    public CircleCollisionComponent(double size, boolean solid, AbstractEntity entity) {
        Main.gameManager.get(CollisionManager.class).list.add(this);
        this.size = size;
        invMass = 1 / (size * size);
        this.solid = solid;
        pc = entity.get(PositionComponent.class);
        vc = entity.get(VelocityComponent.class);
        if (vc == null) {
            vc = new VelocityComponent();
        }
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

    public <E extends AbstractEntity> E placeTouching(Vector2 pos, Class<E> c) {
        Vector2 old = pc.position;
        pc.position = pos;
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
            if (other != this) {
                if (c.isInstance(other.entity)) {
                    if (intersects(other)) {
                        pc.position = old;
                        return (E) other.entity;
                    }
                }
            }
        }
        pc.position = old;
        return null;
    }

    public <E extends AbstractEntity> E touching(Class<E> c) {
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
            if (other != this) {
                if (c.isInstance(other.entity)) {
                    if (intersects(other)) {
                        return (E) other.entity;
                    }
                }
            }
        }
        return null;
    }

    public <E extends AbstractEntity> ArrayList<E> touchingList(Class<E> c) {
        ArrayList<E> r = new ArrayList();
        for (CircleCollisionComponent other : Main.gameManager.getComponent(CollisionManager.class).list) {
            if (other != this) {
                if (c.isInstance(other.entity)) {
                    if (intersects(other)) {
                        r.add((E) other.entity);
                    }
                }
            }
        }
        return r;
    }
}
