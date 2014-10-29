package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.draw.DrawShapeSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.ShapeComponent;
import edu.catlin.springerj.g2e.math.Color;
import edu.catlin.springerj.g2e.math.Vector2;

public class Triangle extends AbstractEntity {

	public Triangle(Vector2[] vert) {
		// Components
		add(new ShapeComponent(vert, new Color(1.0f, 1.0f, 1.0f)));
		
		// Systems
		add(new DrawShapeSystem(this));
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {

	}

}
