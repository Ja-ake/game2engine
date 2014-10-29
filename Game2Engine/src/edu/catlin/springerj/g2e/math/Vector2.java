package edu.catlin.springerj.g2e.math;

import java.lang.Math;

public class Vector2 {

	protected double dX;
	protected double dY;

	// Constructor methods ....

	public Vector2() {
		dX = dY = 0.0;
	}

	public Vector2(double dX, double dY) {
		this.dX = dX;
		this.dY = dY;
	}

	// Convert vector to a string ...

	public String toString() {
		return "Vector2D(" + dX + ", " + dY + ")";
	}

	// Compute magnitude of vector ....

	public double length() {
		return Math.sqrt(dX * dX + dY * dY);
	}

	// Sum of two vectors ....

	public Vector2 add(Vector2 v1) {
		Vector2 v2 = new Vector2(this.dX + v1.dX, this.dY + v1.dY);
		return v2;
	}

	// Subtract vector v1 from v .....

	public Vector2 sub(Vector2 v1) {
		Vector2 v2 = new Vector2(this.dX - v1.dX, this.dY - v1.dY);
		return v2;
	}

	// Scale vector by a constant ...

	public Vector2 scale(double scaleFactor) {
		Vector2 v2 = new Vector2(this.dX * scaleFactor, this.dY * scaleFactor);
		return v2;
	}

	// Normalize a vectors length....

	public Vector2 normalize() {
		Vector2 v2 = new Vector2();

		double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY);
		if (length != 0) {
			v2.dX = this.dX / length;
			v2.dY = this.dY / length;
		}

		return v2;
	}

	// Dot product of two vectors .....

	public double dotProduct(Vector2 v1) {
		return this.dX * v1.dX + this.dY * v1.dY;
	}
	
	public double x() {
		return dX;
	}
	
	public double y() {
		return dY;
	}
}