package edu.catlin.springerj.explore.graphics.particle;

import edu.catlin.springerj.g2e.math.Color4d;
import edu.catlin.springerj.g2e.math.Vector2;

public class Particle {

    public Vector2 position, velocity;
    public double life;
    public double maxlife;
    public Color4d color;
    public boolean blend;

    public Particle(Vector2 pos, Vector2 vel, double l, Color4d color, boolean bl) {
        position = pos;
        velocity = vel;
        life = maxlife = l;
        this.color = color;
        blend = bl;
    }
}
