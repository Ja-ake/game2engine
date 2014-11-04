package edu.catlin.springerj.g2e.object.movement;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class FrictionSystem extends AbstractSystem {
	private FrictionComponent fc;
	private VelocityComponent vc;
	
	@Override
	public void initialize(AbstractEntity ent) {
		vc = ent.getComponent(VelocityComponent.class);
		fc = ent.getComponent(FrictionComponent.class);
	}

	@Override
	public void update() {
		double l = vc.velocity.length();
		if (l - (fc.friction * Core.getDefaultTimer().getDeltaTime()) >= 0.0001) {
			vc.velocity.normalizeSelf().scaleSelf(l - (fc.friction * Core.getDefaultTimer().getDeltaTime()));
		} else {
			vc.velocity.normalizeSelf().scaleSelf(0.0001);
		}
	}
}
