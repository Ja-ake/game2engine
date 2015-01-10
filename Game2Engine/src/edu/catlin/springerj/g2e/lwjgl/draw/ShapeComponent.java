package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.math.Vector2;

public class ShapeComponent extends AbstractComponent {

	public Vector2[] verticies;
	public Color4 color;

	public ShapeComponent(Vector2[] vert) {
		if (vert == null) { throw new RuntimeException("A shape must have at least three verticies!"); }
		if (vert.length < 3) { throw new RuntimeException("A shape must have at least three verticies!"); }

		verticies = new Vector2[vert.length];

		for (int i = 0; i < vert.length; i++) {
			verticies[i] = new Vector2(vert[i].x, vert[i].y);
		}

		color = new Color4(0.0f, 0.0f, 0.0f);
	}

	public ShapeComponent(Vector2[] vert, Color4 c) {
		if (vert == null) { throw new RuntimeException("A shape must have at least three verticies!"); }
		if (vert.length < 3) { throw new RuntimeException("A shape must have at least three verticies!"); }

		verticies = new Vector2[vert.length];

		for (int i = 0; i < vert.length; i++) {
			verticies[i] = new Vector2(vert[i].x, vert[i].y);
		}

		color = new Color4(c.r, c.g, c.b, c.a);
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
