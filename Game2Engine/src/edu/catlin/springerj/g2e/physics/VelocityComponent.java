package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class VelocityComponent extends AbstractComponent {
	public Vector2 velocity;

	public VelocityComponent() {
		this.velocity = new Vector2();
	}

	public VelocityComponent(Vector2 velocity) {
		this.velocity = velocity;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
