package edu.catlin.springerj.g2e.math;

public abstract class G2EMath {
//	/**
//	 * Returns a vector containing the given point expressed in polar coordinates (radius, angle).
//	 */
//	public static Vector2 xytort(Vector2 xy) {
//		return new Vector2(Math.sqrt((float)(xy.x()*xy.x())+(xy.y()*xy.y())), Math.atan(xy.y()/xy.x()));
//	}
//	
//	/**
//	 * Returns a vector containing the given point expressed in Cartesian coordinates (x, y).
//	 */
//	public static Vector2 rttoxy(Vector2 rt) {
//		return new Vector2(rt.x()*Math.cos(rt.y()), rt.x()*Math.sin(rt.y()));
//	}
//	
//	/**
//	 * Returns a vector rotated by theta.
//	 */
//	public static Vector2 xyrot(Vector2 xy, double rot) {
//		return rttoxy(xytort(xy).addSelf(new Vector2(0.0f, rot % (2*Math.PI))));
//	}
//	
//	/**
//	 * Returns whether b is between a and c.
//	 */
//	public static boolean between(double a, double b, double c) {
//		return (a<b&&b<c) || (a>b&&b>c);
//	}
}
