package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class RotationComponent extends AbstractComponent {

	public double rotation;

	public RotationComponent() {
		rotation = 0;
	}

	public RotationComponent(double rot) {
		this.rotation = rot;
	}

	@Override
	public void initialize(AbstractEntity e) {}

}
