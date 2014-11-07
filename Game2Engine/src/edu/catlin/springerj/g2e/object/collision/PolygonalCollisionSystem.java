package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;

public class PolygonalCollisionSystem extends AbstractSystem {
	
	@Override
	public void initialize(AbstractEntity e) {
		this.getRootManager().getManager(PolygonalCollisionManager.class).add(e);
	}

	@Override
	public void update() {
		
	}
}
