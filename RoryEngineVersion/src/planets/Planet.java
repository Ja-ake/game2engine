package planets;

import movement.PositionComponent;
import movement.VelocityComponent;
import collisions.CircleCollisionComponent;
import collisions.CircleCollisionSystem;
import engine.AbstractEntity;
import engine.Main;
import engine.Vector2;
import movement.VelocityMovementSystem;

public class Planet extends AbstractEntity {

    public Planet(Vector2 pos, double size) {
        Main.gameManager.get(PlanetGravityManager.class).list.add(this);
        //Components
        PositionComponent pc = add(new PositionComponent(pos));
        VelocityComponent vc = add(new VelocityComponent());
        CircleCollisionComponent ccc = add(new CircleCollisionComponent(size, true, this));
        PlanetComponent plc = add(new PlanetComponent(pos));
        //Systems
        add(new VelocityMovementSystem(pc, vc));
        add(new CircleCollisionSystem(ccc));
        add(new PlanetRenderSystem(pc, ccc, plc));
        add(new PlanetGravitySystem(pc, vc, plc));
    }
}
