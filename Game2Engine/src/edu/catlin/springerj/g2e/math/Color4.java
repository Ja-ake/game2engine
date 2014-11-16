package edu.catlin.springerj.g2e.math;

public class Color4 {

    public double r;
    public double g;
    public double b;
    public double a;

    public Color4(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color4(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
        a = 1;
    }

    public Color4 copy() {
        return new Color4(r, g, b, a);
    }
}
