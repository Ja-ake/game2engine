package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;

public class FrictionSystem extends AbstractSystem {

	private FrictionComponent fc;
	private VelocityComponent vc;

	@Override
	public void initialize(AbstractEntity e) {
		vc = e.getComponent(VelocityComponent.class);
		fc = e.getComponent(FrictionComponent.class);
	}

	@Override
	public void update() {
		double l = vc.velocity.length();
		if (l - (fc.friction * Core.getDefaultTimer().getDeltaTime()) >= 0.0001) {
			vc.velocity = vc.velocity.multiply(1 - fc.friction * Core.getDefaultTimer().getDeltaTime() / l);
		} else {
			vc.velocity = new Vector2();
		}
	}
}
