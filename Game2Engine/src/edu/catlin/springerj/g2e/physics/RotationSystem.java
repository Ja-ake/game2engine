package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class RotationSystem extends AbstractSystem {

	private RotationComponent rc;
	private RotationalVelocityComponent rvc;

	@Override
	public void initialize(AbstractEntity e) {
		rc = e.get(RotationComponent.class);
		rvc = e.get(RotationalVelocityComponent.class);
	}

	@Override
	public void update() {
		rc.rotation += rvc.velocity * Core.getDefaultTimer().getDeltaTime();
	}

}
