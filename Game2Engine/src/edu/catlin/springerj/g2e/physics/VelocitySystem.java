package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;

public class VelocitySystem extends AbstractSystem {

	private PositionComponent pc;
	private VelocityComponent vc;
	private ComponentVelocityComponent cvc;

	@Override
	public void initialize(AbstractEntity e) {
		pc = e.getComponent(PositionComponent.class);
		try {
			vc = e.getComponent(VelocityComponent.class);
		} catch (InvalidComponentException ice) {
			vc = null;
		}
		try {
			cvc = e.getComponent(ComponentVelocityComponent.class);
		} catch (InvalidComponentException ice) {
			cvc = null;
		}
		if (vc == null && cvc == null) e.getComponent(VelocityComponent.class);
	}

	@Override
	public void update() {
		if (vc != null) pc.position = pc.position.add(vc.velocity.multiply(Core.getDefaultTimer().getDeltaTime()));
		else if (cvc != null) pc.position = pc.position.add(cvc.velocity().multiply(Core.getDefaultTimer().getDeltaTime()));
	}
}
