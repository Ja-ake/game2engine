package edu.catlin.springerj.g2e.math;

public class Color4d {

    public double r;
    public double g;
    public double b;
    public double a;

    public Color4d(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color4d(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
        a = 1;
    }

    public Color4d copy() {
        return new Color4d(r, g, b, a);
    }
}
