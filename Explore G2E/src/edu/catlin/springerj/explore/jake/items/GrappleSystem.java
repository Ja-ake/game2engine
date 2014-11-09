package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class GrappleSystem extends AbstractSystem {

	private LengthComponent glc;
	private PositionComponent pc;
	private RotationComponent rc;
	private VelocityComponent vc;
	private SpriteComponent sc;
	
	@Override
	public void initialize(AbstractEntity e) {
		glc = e.get(LengthComponent.class);
		pc = e.get(PositionComponent.class);
		rc = e.get(RotationComponent.class);
		vc = e.get(VelocityComponent.class);
		sc = e.get(SpriteComponent.class);
		
		rc.rot = vc.velocity.direction() - Math.PI/2;
	}

	@Override
	public void update() {
		Graphics.drawLine(pc.position.x, pc.position.y, glc.start.x, glc.start.y, 0.0d, 0.0d, 0.0d, sc.alpha);
	}

}
