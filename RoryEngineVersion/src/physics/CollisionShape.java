package physics;

import engine.Vector2;
import java.util.ArrayList;

public interface CollisionShape {

    public abstract AABB getAABB();

    public abstract ArrayList<Vector2> getProjAxes(CollisionShape other);

    public abstract ProjectionRange getProjRange(Vector2 axis);
    
    public abstract void setPos(Vector2 pos);
    
    public abstract void setRot(double rot);
}
