package edu.catlin.springerj.g2e.object.physics;

import edu.catlin.springerj.g2e.math.Vector2;
import java.util.ArrayList;

public class Circle implements CollisionShape {

    private double radius;
    private Vector2 pos;

    public Circle(double radius) {
        this.radius = radius;
        pos = new Vector2();
    }

    @Override
    public AABB getAABB() {
        return new AABB(pos.subtract(new Vector2(radius, radius)), pos.add(new Vector2(radius, radius)));
    }

    @Override
    public ArrayList<Vector2> getProjAxes(CollisionShape other) {
        ArrayList<Vector2> a = new ArrayList();
        if (other instanceof Circle) {
            Circle c = (Circle) other;
            a.add(pos.subtract(c.pos));
        } else if (other instanceof Polygon) {
            Polygon p = (Polygon) other;
            Vector2 point = p.getPoint(0);
            for (Vector2 v : p.getPointList()) {
                if (v.subtract(pos).lengthSquared() < point.subtract(pos).lengthSquared()) {
                    point = v;
                }
            }
            a.add(pos.subtract(point));
        }
        return a;
    }

    @Override
    public ProjectionRange getProjRange(Vector2 axis) {
        double x = pos.dot(axis);
        double y = pos.dot(axis.normal());
        ProjectionRange r = new ProjectionRange();
        r.max = x + radius;
        r.min = x - radius;
        r.max_high = r.max_low = r.min_high = r.min_low = y;
        return r;
    }

    @Override
    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    @Override
    public void setRot(double rot) {
        //Lol nope
    }

}
