package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class FrictionComponent extends AbstractComponent {

	public double friction;

	public FrictionComponent() {
		friction = 10;
	}

	public FrictionComponent(double f) {
		friction = f;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
