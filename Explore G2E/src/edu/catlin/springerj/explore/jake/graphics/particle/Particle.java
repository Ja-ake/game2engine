package edu.catlin.springerj.explore.jake.graphics.particle;

import edu.catlin.springerj.g2e.math.Vector2;

public class Particle {
	public Vector2 position, velocity;
	public double life;
	public double maxlife;
	public double red, blue, green, alpha;
	
	public Particle(Vector2 pos, Vector2 vel, double l, double r, double g, double b) {
		position = pos;
		velocity = vel;
	}
}
