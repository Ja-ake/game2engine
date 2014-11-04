package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractComponent;

public class CircularCollisionComponent extends AbstractComponent {
	public double radius;
	
	public CircularCollisionComponent() {
		this(1.0f);
	}
	
	public CircularCollisionComponent(double r) {
		radius = r;
	}
}
