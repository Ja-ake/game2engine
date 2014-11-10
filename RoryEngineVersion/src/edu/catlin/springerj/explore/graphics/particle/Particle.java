package edu.catlin.springerj.explore.graphics.particle;

import engine.Color4d;
import engine.Vector2;

public class Particle {

    public Vector2 position, velocity;
    public double life;
    public double maxlife;
    public Color4d color;

    public Particle(Vector2 pos, Vector2 vel, double l, Color4d color) {
        position = pos;
        velocity = vel;
        life = maxlife = l;
        this.color = color;
    }
}
