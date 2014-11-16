package particles;

import engine.Color4d;
import engine.Vector2;

public class Particle {

    public Vector2 position, velocity;
    public int life, maxLife;
    public Color4d color;

    public Particle(Vector2 pos, Vector2 vel, int life, Color4d color) {
        position = pos;
        velocity = vel;
        this.life = maxLife = life;
        this.color = color;
    }
}
