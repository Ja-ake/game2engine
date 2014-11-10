package systems;

import components.PhysicsComponent;
import components.PhysicsManagerComponent;
import engine.AbstractSystem;
import engine.Main;
import engine.Vector2;
import java.util.ArrayList;
import physics.CollisionInfo;
import physics.ProjectionRange;

public class SATCollisionSolverSystem extends AbstractSystem {

    private PhysicsComponent physics;

    public SATCollisionSolverSystem(PhysicsComponent physics) {
        this.physics = physics;
    }

    private void collide(PhysicsComponent obj1, PhysicsComponent obj2) {
        CollisionInfo collisionInfo = null;
        //Get all the axes to check along
        ArrayList<Vector2> projAxes = obj1.collisionShape.getProjAxes(obj2.collisionShape);
        //Reverse because the normals should p1 the direction for obj2 to travel
        for (int i = 0; i < projAxes.size(); i++) {
            projAxes.set(i, projAxes.get(i).reverse());
        }
        projAxes.addAll(obj2.collisionShape.getProjAxes(obj1.collisionShape));
        //Check each axis
        for (Vector2 axis : projAxes) {
            axis = axis.normalize();
            //Get the projected range for each object along the axis
            ProjectionRange pr1 = obj1.collisionShape.getProjRange(axis);
            ProjectionRange pr2 = obj2.collisionShape.getProjRange(axis);
            //Check how much the ranges are penetrating
            double depth = pr1.intersection(pr2);
            //If they are not penetrating, the objects aren't colliding
            if (depth == 0) {
                return;
            }
            //If the depth is the smallest
            if (collisionInfo == null || depth < collisionInfo.depth) {
                //Calculate the points to apply forces at
                Vector2 p1 = axis.multiply(pr1.max).add(axis.normal().multiply(pr1.collisionHeight(pr2)));
                Vector2 p2 = axis.multiply(pr1.min).add(axis.normal().multiply(pr1.collisionHeight(pr2)));
                //Set the collision info
                collisionInfo = new CollisionInfo(obj1, obj2, p1, p2, axis, depth);
            }
        }
        //If there is a collision
        if (collisionInfo != null) {
            //System.out.println(collisionInfo.p1);
            //Fix it
            resolveCollision(collisionInfo);
        } else {
            System.out.println("Problem?");
        }
    }

    public void resolveCollision(CollisionInfo collisionInfo) {
        //Variables
        PhysicsComponent obj1 = collisionInfo.obj1;
        PhysicsComponent obj2 = collisionInfo.obj2;
        Vector2 p1 = collisionInfo.p1;
        Vector2 p2 = collisionInfo.p2;
        Vector2 normal = collisionInfo.normal;
        double depth = collisionInfo.depth;
        double rapnn = p1.subtract(obj1.position.position).cross(normal);
        double rbpnn = p2.subtract(obj2.position.position).normal().dot(normal);
        //Calculate the effective combined velocity
        Vector2 vel = obj1.velocityAt(p1).subtract(obj2.velocityAt(p2));
        //Complicated impulse formula
        //Don't debug or try to understand, just hope it's right
        double impulse = -(2 - (1 - obj1.restitution) * (1 - obj1.restitution)) * vel.dot(normal)
                / (obj1.invMass + obj2.invMass + rapnn * rapnn * obj1.invRotMass + rbpnn * rbpnn * obj2.invRotMass);
        //Apply impulses to objects
        obj1.applyImpulse(p1, normal.multiply(impulse));
        obj2.applyImpulse(p2, normal.multiply(-impulse));
        //Translate the objects to not colliding
        //if (depth > 2 * Util.SMALL) {
        //depth *= .5;
        obj1.position.position = obj1.position.position.subtract(normal.multiply(depth * obj1.invMass / (obj1.invMass + obj2.invMass)));
        obj2.position.position = obj2.position.position.add(normal.multiply(depth * obj2.invMass / (obj1.invMass + obj2.invMass)));
        //}
//        System.out.println("Obj1: " + obj1.collisionShape.getClass().getSimpleName());
//        System.out.println("Normal: " + normal);
//        System.out.println("Impulse: " + impulse);
//        System.out.println("Point 1: " + p1);
//        System.out.println("Obj1 position: " + obj1.position.position);
//        System.out.println("Obj1 velocity: " + vel);
//        System.out.println("Obj1 rotation: " + obj1.rotation.rot);
//        System.out.println("Obj1 rotation speed: " + obj1.rotation.aVel);
    }

    @Override
    public void update() {
        physics.collisionShape.setPos(physics.position.position);
        physics.collisionShape.setRot(physics.rotation.rot);
        for (int i = 0; i < 1; i++) {
            for (PhysicsComponent other : Main.gameManager.getComponent(PhysicsManagerComponent.class).physicsComponentList) {
                if (other != physics) {
                    if (physics.collisionShape.getAABB().intersects(other.collisionShape.getAABB())) {
                        collide(physics, other);
                    }
                }
                physics.collisionShape.setPos(physics.position.position);
                physics.collisionShape.setRot(physics.rotation.rot);
            }
        }
    }
}
