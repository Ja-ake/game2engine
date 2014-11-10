package entities;

import components.PhysicsComponent;
import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import engine.AbstractEntity;
import engine.Vector2;
import physics.Circle;
import systems.*;

public class Player extends AbstractEntity {

    public Player() {
        //Components
        PositionComponent pc = add(new PositionComponent(new Vector2(100, 100)));
        VelocityComponent vc = add(new VelocityComponent());
        RotationComponent rc = add(new RotationComponent());
        rc.rot = Math.PI / 4;
        SpriteComponent sc = add(new SpriteComponent("circle"));
        PhysicsComponent pyc = add(new PhysicsComponent(pc, vc, rc, new Circle(16)));
        pyc.invMass = 1;
        pyc.invRotMass = .1;
        //Systems
        add(new PlayerControlSystem(pc, vc, sc));
        add(new FrictionSystem(vc, rc));
        add(new VelocityMovementSystem(pc, vc));
        add(new RotationSystem(rc));
        add(new SATCollisionSolverSystem(pyc));
        add(new SpriteRenderSystem(pc, rc, sc));
    }
}
