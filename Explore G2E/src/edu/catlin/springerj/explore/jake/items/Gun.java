package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.g2e.core.AbstractEntity;

public class Gun extends AbstractEntity {

	public Gun(AbstractEntity holder) {
		this(holder, GunComponent.TYPE_FIRE);
	}
	
	public Gun(AbstractEntity holder, int type) {
		add(new GunComponent(holder, type));
		
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		
	}

}
