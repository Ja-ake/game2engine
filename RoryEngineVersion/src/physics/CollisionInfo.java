package physics;

import components.PhysicsComponent;
import engine.Vector2;

public class CollisionInfo {

    public PhysicsComponent obj1;
    public PhysicsComponent obj2;
    public Vector2 p1;
    public Vector2 p2;
    public Vector2 normal;
    public double depth;

    public CollisionInfo(PhysicsComponent obj1, PhysicsComponent obj2, Vector2 p1, Vector2 p2, Vector2 normal, double depth) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.p1 = p1;
        this.p2 = p2;
        this.normal = normal;
        this.depth = depth;
    }

}
