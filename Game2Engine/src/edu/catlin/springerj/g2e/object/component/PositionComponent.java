package edu.catlin.springerj.g2e.object.component;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.math.Vector2;

public class PositionComponent extends AbstractComponent {
	public Vector2 position;
	
	public PositionComponent(Vector2 pos) {
		position = new Vector2(pos.x(), pos.y());
	}
	
	public PositionComponent() {
		position = new Vector2(0.0f, 0.0f);
	}
}
