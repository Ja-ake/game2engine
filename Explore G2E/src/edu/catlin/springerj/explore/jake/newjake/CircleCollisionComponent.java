package edu.catlin.springerj.explore.jake.newjake;

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
