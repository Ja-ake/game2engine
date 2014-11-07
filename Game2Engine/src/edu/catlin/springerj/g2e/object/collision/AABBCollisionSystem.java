package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.web.WebManager;

public class AABBCollisionSystem extends AbstractSystem {
	
	@Override
	public void initialize(AbstractEntity ent) {
		if (this.getManager().getManager(WebManager.class).get("AABBCollisionWeb") == null) this.getManager().getManager(WebManager.class).register(new AABBCollisionWeb());

		this.getRootManager().getManager(WebManager.class).add(ent);
	}

	@Override
	public void update() {
		
	}
}
