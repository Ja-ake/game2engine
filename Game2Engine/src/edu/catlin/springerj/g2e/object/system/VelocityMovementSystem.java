package edu.catlin.springerj.g2e.object.system;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.object.component.PositionComponent;
import edu.catlin.springerj.g2e.object.component.VelocityComponent;

public class VelocityMovementSystem extends AbstractSystem {
	private PositionComponent pc;
	private VelocityComponent vc;
	
	
	public VelocityMovementSystem(AbstractEntity e) {
		pc = e.getComponent(PositionComponent.class);
		vc = e.getComponent(VelocityComponent.class);
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		pc.position.addSelf(vc.velocity.scale(Core.getDefaultTimer().getDeltaTime()));
	}
}
