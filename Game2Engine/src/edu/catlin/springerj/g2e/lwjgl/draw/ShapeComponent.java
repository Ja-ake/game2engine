package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4d;
import edu.catlin.springerj.g2e.math.Vector2;

public class ShapeComponent extends AbstractComponent {

    public Vector2[] verticies;
    public Color4d color;

    @Override
    public void initialize(AbstractEntity e) {

    }

    public ShapeComponent(Vector2[] vert) {
        if (vert == null) {
            throw new RuntimeException("A shape must have at least three verticies!");
        }
        if (vert.length < 3) {
            throw new RuntimeException("A shape must have at least three verticies!");
        }

        verticies = new Vector2[vert.length];

        for (int i = 0; i < vert.length; i++) {
            verticies[i] = new Vector2(vert[i].x, vert[i].y);
        }

        color = new Color4d(0.0f, 0.0f, 0.0f);
    }

    public ShapeComponent(Vector2[] vert, Color4d c) {
        if (vert == null) {
            throw new RuntimeException("A shape must have at least three verticies!");
        }
        if (vert.length < 3) {
            throw new RuntimeException("A shape must have at least three verticies!");
        }

        verticies = new Vector2[vert.length];

        for (int i = 0; i < vert.length; i++) {
            verticies[i] = new Vector2(vert[i].x, vert[i].y);
        }

        color = new Color4d(c.r, c.g, c.b, c.a);
    }
}
