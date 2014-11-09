package edu.catlin.springerj.explore.graphics.particle;

import edu.catlin.springerj.g2e.math.Vector2;

public class Particle {
	public Vector2 position, velocity;
	public double life;
	public double maxlife;
	public double red, blue, green, alpha;
	public boolean blend;
	
	public Particle(Vector2 pos, Vector2 vel, double l, double r, double g, double b, double a, boolean bl) {
		position = pos;
		velocity = vel;
		life = maxlife = l;
		red = r;
		green = g;
		blue = b;
                alpha = a;
		blend = bl;
	}
}
