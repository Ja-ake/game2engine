package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class CircleCollisionComponent extends AbstractComponent {

    public double size;
    public double invMass;
    public PositionComponent pc;
    public VelocityComponent vc;

    public CircleCollisionComponent(double size) {
        this.size = size;
        invMass = 1 / (size * size);
    }

    @Override
    public void initialize(AbstractEntity e) {
        System.out.println("iuherpiguheprbiuhwpeiuhgpwieughpwiueg");
        pc = e.get(PositionComponent.class);
        vc = e.get(VelocityComponent.class);
    }

    public boolean touching(CircleCollisionComponent other) {
        System.out.println(pc.position);
        //lengthSquared is faster than length
        return (size + other.size) * (size + other.size) < pc.position.subtract(other.pc.position).lengthSquared();
    }

}
