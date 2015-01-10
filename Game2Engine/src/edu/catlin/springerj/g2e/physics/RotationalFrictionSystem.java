package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;

public class RotationalFrictionSystem extends AbstractSystem {

	private RotationalVelocityComponent rvc;
	private RotationalFrictionComponent rfc;

	@Override
	public void initialize(AbstractEntity e) {
		rvc = e.get(RotationalVelocityComponent.class);
		rfc = e.get(RotationalFrictionComponent.class);
	}

	@Override
	public void update() {
		double potential = 0.0d;
		if (Math.abs(potential = rvc.velocity * (1 - rfc.friction * Core.getDefaultTimer().getDeltaTime() / rvc.velocity)) >= 0.0001d) {
			rvc.velocity = potential;
		} else {
			rvc.velocity = 0.0d;
		}
	}

}
