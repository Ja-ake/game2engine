package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class RotationalFrictionComponent extends AbstractComponent {

	public double friction;

	public RotationalFrictionComponent() {
		friction = 10;
	}

	public RotationalFrictionComponent(double f) {
		friction = f;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
