package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class StaticImageRenderSystem extends AbstractSystem {

	private PositionComponent pc;
	private SpriteComponent sc;
	
	@Override
	public void initialize(AbstractEntity e) {
		pc = e.get(PositionComponent.class);
	}

	@Override
	public void update() {
		
	}

}
