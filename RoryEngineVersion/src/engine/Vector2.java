package engine;

public class Vector2 {

    public final double x;
    public final double y;

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public double cross(Vector2 other) {
        return x * other.y - y * other.x;
    }

    public double direction() {
        return Math.atan2(y, x);
    }

    public double dot(Vector2 other) {
        return x * other.x + y * other.y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public Vector2 multiply(double d) {
        return new Vector2(x * d, y * d);
    }

    public Vector2 normal() {
        return new Vector2(-y, x);
    }

    public Vector2 normalize() {
        return multiply(1 / length());
    }

    public Vector2 reverse() {
        return new Vector2(-x, -y);
    }

    public Vector2 setLength(double l) {
        return multiply(l / length());
    }

    public Vector2 setX(double x) {
        return new Vector2(x, y);
    }

    public Vector2 setY(double y) {
        return new Vector2(x, y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
