package edu.catlin.springerj.g2e.lwjgl.view;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.lwjgl.WindowSystem;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class TargetedViewSystem extends AbstractSystem {
	private EntityTargetComponent etc;
	private PositionComponent pc;
	
	private WindowSystem ws;
	private WindowComponent wc;
	
	@Override
	public void initialize(AbstractEntity e) {
		etc = e.get(EntityTargetComponent.class);
		pc = e.get(PositionComponent.class);
		
		ws = ((LWJGLManager) this.getRootManager()).getWindow().getSystem(WindowSystem.class);
		wc = ((LWJGLManager) this.getRootManager()).getWindow().getComponent(WindowComponent.class);
	}

	@Override
	public void update() {
		//if (pc.position.subtract(etc.getTargetPosition()).length() > 10) 
		pc.position = pc.position.add(etc.getTargetPosition().subtract(pc.position).multiply(0.9*Core.getDefaultTimer().getDeltaTime()));
		ws.setViewport(-wc.width/2+pc.position.x, wc.width/2+pc.position.x, -wc.height/2+pc.position.y, wc.height/2+pc.position.y);
		//System.out.println((-wc.width/2+pc.position.x) + " " + (wc.width/2+pc.position.x) + " " + (-wc.height/2+pc.position.y) + " " + (wc.height/2+pc.position.y));
	}
}
