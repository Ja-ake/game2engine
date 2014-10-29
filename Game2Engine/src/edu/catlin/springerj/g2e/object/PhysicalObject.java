package edu.catlin.springerj.g2e.object;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.component.AABBComponent;
import edu.catlin.springerj.g2e.object.component.PositionComponent;
import edu.catlin.springerj.g2e.object.component.RotationComponent;
import edu.catlin.springerj.g2e.object.component.RotationVelocityComponent;
import edu.catlin.springerj.g2e.object.component.VelocityComponent;
import edu.catlin.springerj.g2e.object.system.VelocityMovementSystem;

public class PhysicalObject extends AbstractEntity {

	public PhysicalObject(Vector2 position) {
		
		// Components
		add(new SpriteComponent());
		add(new PositionComponent(position));
		add(new RotationComponent(/*0.0f*/));
		add(new VelocityComponent(new Vector2(10.0f, 1.0f)));
		add(new RotationVelocityComponent(/*new Vector2(0.0f, 0.0f)*/));
		add(new AABBComponent(/*0.0f, 0.0f, 1.0f, 1.0f*/));
		
		// Systems
		add(new SpriteRenderSystem(this, "wall_block")); // SpriteComponent
		add(new VelocityMovementSystem(this));
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		
	}

}
