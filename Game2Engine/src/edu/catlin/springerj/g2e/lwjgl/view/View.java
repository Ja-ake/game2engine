package edu.catlin.springerj.g2e.lwjgl.view;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.ZoomComponent;

public class View extends AbstractEntity {
	
	public View(AbstractEntity target) {
		add(new EntityTargetComponent(target));
		add(new PositionComponent());
		add(new ZoomComponent());
	}
	
	@Override
	public void initialize() {
		add(new TargetedViewSystem());
	}

	@Override
	public void update() {
		
	}

}
