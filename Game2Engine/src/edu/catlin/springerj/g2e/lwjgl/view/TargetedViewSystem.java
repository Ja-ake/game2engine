package edu.catlin.springerj.g2e.lwjgl.view;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.lwjgl.WindowSystem;
import edu.catlin.springerj.g2e.physics.PositionComponent;

public class TargetedViewSystem extends AbstractSystem implements EventListener<MouseEvent> {
	private EntityTargetComponent etc;
	private PositionComponent pc;
	private ZoomComponent zc;

	private WindowSystem ws;
	private WindowComponent wc;

	@Override
	public void initialize(AbstractEntity e) {
		etc = e.get(EntityTargetComponent.class);
		pc = e.get(PositionComponent.class);
		zc = e.get(ZoomComponent.class);

		ws = ((LWJGLManager) this.getRootManager()).getWindow().getSystem(WindowSystem.class);
		wc = ((LWJGLManager) this.getRootManager()).getWindow().getComponent(WindowComponent.class);

		this.getRootManager().getManager(EventManager.class).register(this);
	}

	@Override
	public void onEvent(MouseEvent event) {
		if (event.action == 15) {
			if (event.button > 0) zc.zoom /= 1.1;
			else zc.zoom *= 1.1;
		}
	}

	@Override
	public void update() {
		pc.position = pc.position.add(etc.getTargetPosition().subtract(pc.position).multiply(.9 * Core.getDefaultTimer().getDeltaTime()));
		ws.setViewport(-wc.width * zc.zoom / 2 + pc.position.x, wc.width * zc.zoom / 2 + pc.position.x, -wc.height * zc.zoom / 2 + pc.position.y, wc.height * zc.zoom / 2 + pc.position.y);
	}
}
