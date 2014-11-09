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
import edu.catlin.springerj.g2e.utility.Logger;

public class Grapple extends AbstractEntity {
	
	public Grapple(Vector2 position, Vector2 velocity) {
		add(new PositionComponent(position));
		add(new VelocityComponent(velocity));
		add(new FrictionComponent(1.0d));
		add(new RotationComponent());
		add(new SpriteComponent("grapplehead"));
		add(new LengthComponent(position));
	}
	
	@Override
	public void initialize() {
		add(new SpriteRenderSystem());
		add(new FrictionSystem());
		add(new VelocityMovementSystem());
		add(new GrappleSystem());
	}

	@Override
	public void update() {
		if (get(LengthComponent.class).length > 75.0d) {
			get(VelocityComponent.class).velocity = get(VelocityComponent.class).velocity.setLength(0.00001);
			get(SpriteComponent.class).alpha = 0.5d;
		}
		
		get(LengthComponent.class).length += get(VelocityComponent.class).velocity.length() * Core.getDefaultTimer().getDeltaTime();
		
		if ((get(VelocityComponent.class).velocity).length() < 0.001) get(SpriteComponent.class).alpha -= Core.getDefaultTimer().getDeltaTime()/1;
		if (get(SpriteComponent.class).alpha < 0.0d) this.getManager().remove(this);
	}
}
