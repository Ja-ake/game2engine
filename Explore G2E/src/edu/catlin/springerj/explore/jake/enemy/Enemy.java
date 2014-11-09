package edu.catlin.springerj.explore.jake.enemy;

import edu.catlin.springerj.explore.jake.items.BulletRechargeComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.lwjgl.view.EntityTargetComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class Enemy extends AbstractEntity {

	public Enemy(Vector2 position, AbstractEntity e) {
		// Components
		add(new PositionComponent(position));
		add(new VelocityComponent());
		add(new RotationComponent());
		add(new SpriteComponent("enemy_smoking_black", 5, 2.0d));
		add(new EntityTargetComponent(e));
		add(new BulletRechargeComponent(2.5d));
		add(new HealthComponent(5.0d));
	}
	
	@Override
	public void initialize() {		
		add(new SpriteRenderSystem());
		add(new VelocityMovementSystem());
		add(new AIFlyingSineSystem());
		add(new OnDeathKillSystem());
	}

	@Override
	public void update() {
		
	}
	
}
