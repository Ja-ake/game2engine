package edu.catlin.springerj.g2e.lwjgl.view;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;

public class EntityTargetComponent extends AbstractComponent {
	public AbstractEntity entity;
	private PositionComponent position;

	public EntityTargetComponent(AbstractEntity e) {
		try {
			position = e.getComponent(PositionComponent.class);
		} catch (InvalidComponentException ex) {
			throw new RuntimeException("Invalid target: does not contain PositionComponent");
		}
		entity = e;
	}

	public Vector2 getTargetPosition() {
		return position.position;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
