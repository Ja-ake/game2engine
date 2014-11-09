package edu.catlin.springerj.explore.jake.enemy;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class Enemy extends AbstractEntity {

	public Enemy(Vector2 position) {
		// Components
		add(new PositionComponent(position));
		add(new VelocityComponent());
		add(new RotationComponent());
		add(new SpriteComponent("enemy_smoking_black", 5, 2.0d));
	}
	
	@Override
	public void initialize() {
		add(new SpriteRenderSystem());
		add(new VelocityMovementSystem());
		add(new AIFlyingSineSystem());
	}

	@Override
	public void update() {
		
	}
	
}
