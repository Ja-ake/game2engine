package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.FrictionComponent;
import edu.catlin.springerj.g2e.movement.FrictionSystem;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class Grapple extends AbstractEntity {
	
	public Grapple(Vector2 position, Vector2 velocity) {
		add(new PositionComponent(position));
		add(new VelocityComponent(velocity));
		add(new FrictionComponent(1.0d));
		add(new RotationComponent(0.0d));
		add(new SpriteComponent("sprite/grapplehead"));
		add(new GrappleLengthComponent());
	}
	
	@Override
	public void initialize() {
		add(new SpriteRenderSystem());
		add(new FrictionSystem());
		add(new VelocityMovementSystem());
	}

	@Override
	public void update() {
		if (getComponent(GrappleLengthComponent.class).length > 100.0d) this.getManager().remove(this);
		getComponent(GrappleLengthComponent.class).length += getComponent(VelocityComponent.class).velocity.length() * Core.getDefaultTimer().getDeltaTime();
	}
}
