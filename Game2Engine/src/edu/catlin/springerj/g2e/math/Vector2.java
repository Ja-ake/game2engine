package edu.catlin.springerj.g2e.math;

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
		return "(" + (float) x + ", " + (float) y + ")";
	}
}
/*
 * public static final Vector2 NORTH = new Vector2(0.0f, 1.0f); public static
 * final Vector2 SOUTH = new Vector2(0.0f, -1.0f); public static final Vector2
 * EAST = new Vector2(1.0f, 0.0f); public static final Vector2 WEST = new
 * Vector2(-1.0f, 0.0f);
 * 
 * protected double dX; protected double dY;
 * 
 * // Constructor methods ....
 * 
 * public Vector2() { dX = dY = 0.0; }
 * 
 * public Vector2(double dX, double dY) { this.dX = dX; this.dY = dY; }
 * 
 * // Convert vector to a string ...
 * 
 * public String toString() { return "Vector2D(" + dX + ", " + dY + ")"; }
 * 
 * // Compute magnitude of vector ....
 * 
 * public double length() { return Math.sqrt(dX * dX + dY * dY); }
 * 
 * // Sum of two vectors ....
 * 
 * public Vector2 add(Vector2 v1) { Vector2 v2 = new Vector2(this.dX + v1.dX,
 * this.dY + v1.dY); return v2; }
 * 
 * // Subtract vector v1 from v .....
 * 
 * public Vector2 sub(Vector2 v1) { Vector2 v2 = new Vector2(this.dX - v1.dX,
 * this.dY - v1.dY); return v2; }
 * 
 * // Scale vector by a constant ...
 * 
 * public Vector2 scale(double scaleFactor) { Vector2 v2 = new Vector2(this.dX *
 * scaleFactor, this.dY * scaleFactor); return v2; }
 * 
 * // Normalize a vectors length....
 * 
 * public Vector2 normalize() { Vector2 v2 = new Vector2();
 * 
 * double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY); if (length
 * != 0) { v2.dX = this.dX / length; v2.dY = this.dY / length; }
 * 
 * return v2; }
 * 
 * // Sum of two vectors ....
 * 
 * public Vector2 addSelf(Vector2 v1) { this.dX += v1.dX; this.dY += v1.dY;
 * return this; }
 * 
 * // Subtract vector v1 from v .....
 * 
 * public Vector2 subSelf(Vector2 v1) { this.dX -= v1.dX; this.dY -= v1.dY;
 * return this; }
 * 
 * // Scale vector by a constant ...
 * 
 * public Vector2 scaleSelf(double scaleFactor) { this.dX *= scaleFactor;
 * this.dY *= scaleFactor; return this; }
 * 
 * // Normalize a vectors length....
 * 
 * public Vector2 normalizeSelf() { double length = Math.sqrt(this.dX * this.dX
 * + this.dY * this.dY); if (length != 0) { this.dX = this.dX / length; this.dY
 * = this.dY / length; }
 * 
 * return this; }
 * 
 * // Dot product of two vectors .....
 * 
 * public double dotProduct(Vector2 v1) { return this.dX * v1.dX + this.dY *
 * v1.dY; }
 * 
 * public Vector2 normal() { return new Vector2(dY, -dX); }
 * 
 * public Vector2 normalSelf() { double dx = dX, dy = dY; dX = dy; dY = -dx;
 * return this; }
 * 
 * public double x() { return dX; }
 * 
 * public double y() { return dY; }
 */
