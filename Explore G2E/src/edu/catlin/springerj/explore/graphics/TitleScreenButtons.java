package edu.catlin.springerj.explore.graphics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.draw.StaticImageRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class TitleScreenButtons extends AbstractEntity implements EventListener<MouseEvent> {

	@Override
	public void initialize() {
		add(new PositionComponent(new Vector2(-480.0d, -12.5d)));
		add(new SpriteComponent("arrow", 4, 1.5d));
		
		add(new StaticImageRenderSystem());
		
		Core.getRootManager().getManager(EventManager.class).register(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onEvent(MouseEvent event) {
		PositionComponent pc = get(PositionComponent.class);
		if (event.action == MouseEvent.ACTION_RELEASE) {
			if (pc.position.y == -12.5d) ((LWJGLManager) Core.getRootManager()).setRoom("tutorial00");
			else System.exit(0);
		} else if (event.action == MouseEvent.ACTION_OTHER) {
			if (pc.position.y == -12.5d) pc.position = new Vector2(pc.position.x, -107.0d);
			else pc.position = new Vector2(pc.position.x, -12.5d);
		}
	}
}
