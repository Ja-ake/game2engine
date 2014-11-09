package edu.catlin.springerj.explore.jake.enemy;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class AIFlyingSineSystem extends AbstractSystem {

	private VelocityComponent vc;
	
	@Override
	public void initialize(AbstractEntity e) {
		vc = e.get(VelocityComponent.class);
	}

	@Override
	public void update() {
		vc.velocity = new Vector2(0.0d, 32.0d*Math.sin(Core.getDefaultTimer().getCurrentTime()*1.6d));
	}

}
