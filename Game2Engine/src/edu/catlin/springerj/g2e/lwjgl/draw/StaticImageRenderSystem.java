package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.physics.PositionComponent;

public class StaticImageRenderSystem extends AbstractSystem {

	private PositionComponent pc;
	private SpriteComponent sc;
	private WindowComponent wc;

	@Override
	public void initialize(AbstractEntity e) {
		pc = e.get(PositionComponent.class);
		sc = e.get(SpriteComponent.class);
		wc = ((LWJGLManager) Core.getRootManager()).getWindow().get(WindowComponent.class);
	}

	@Override
	public void update() {
		if (sc.visible) Graphics.drawSprite(sc.getTexture(), (wc.centerx) + pc.position.x, (wc.centery) + pc.position.y, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d);
		sc.imageIndex += Core.getDefaultTimer().getDeltaTime() * sc.imageSpeed;
	}

}
