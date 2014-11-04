package edu.catlin.springerj.g2e.object.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;

public class FrictionComponent extends AbstractComponent {
	public double friction;
	
	public FrictionComponent() {
		friction = 0.05f;
	}
	
	public FrictionComponent(double f) {
		friction = f;
	}
}
