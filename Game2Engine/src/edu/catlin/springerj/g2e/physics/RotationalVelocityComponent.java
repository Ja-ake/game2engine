package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class RotationalVelocityComponent extends AbstractComponent {

	public double velocity; // positive = counter-clockwise; measured in
							// radians/second

	@Override
	public void initialize(AbstractEntity e) {

	}
}
