package edu.catlin.springerj.g2e.physics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class PositionComponent extends AbstractComponent {

	public Vector2 position;

	public PositionComponent() {
		this.position = new Vector2();
	}

	public PositionComponent(Vector2 position) {
		this.position = position;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
