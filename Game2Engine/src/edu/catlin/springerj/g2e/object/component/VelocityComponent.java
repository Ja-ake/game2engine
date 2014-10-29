package edu.catlin.springerj.g2e.object.component;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.math.Vector2;

public class VelocityComponent extends AbstractComponent {
	public Vector2 velocity;
	
	public VelocityComponent(Vector2 vel) {
		velocity = new Vector2(vel.x(), vel.y());
	}
	
	public VelocityComponent() {
		velocity = new Vector2(0.0f, 0.0f);
	}
}
