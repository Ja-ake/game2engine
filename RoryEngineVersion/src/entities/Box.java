package entities;

import components.GravityComponent;
import components.PhysicsComponent;
import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import engine.AbstractEntity;
import engine.Vector2;
import physics.Circle;
import systems.FrictionSystem;
import systems.GravitySystem;
import systems.VelocityMovementSystem;
import systems.SpriteRenderSystem;
import systems.RotationSystem;
import systems.SATCollisionSolverSystem;

public class Box extends AbstractEntity {

    public Box(Vector2 pos) {
        //Components
        PositionComponent pc = new PositionComponent(pos);
        VelocityComponent vc = new VelocityComponent();
        GravityComponent gc = new GravityComponent();
        RotationComponent rc = new RotationComponent();
        SpriteComponent sc = add(new SpriteComponent("wall_block"));
        PhysicsComponent pyc = add(new PhysicsComponent(pc, vc, rc, new Circle(16)));//new Polygon(4, 16)));
        pyc.invMass = 1;
        //pyc.invRotMass = .01;
        pyc.restitution = .5;
        //Systems
        add(new GravitySystem(vc, gc));
        add(new FrictionSystem(vc,rc));
        add(new VelocityMovementSystem(pc, vc));
        add(new RotationSystem(rc));
        add(new SATCollisionSolverSystem(pyc));
        add(new SpriteRenderSystem(pc, rc, sc));
    }
}
