package edu.catlin.springerj.explore.jake.graphics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class FilledCircle extends AbstractEntity {

	@Override
	public void initialize() {
		add(new PositionComponent(new Vector2(0.0d, 0.0d)));
		add(new CircleComponent(100.0d));
		
		add(new FilledCircleRenderSystem());
	}

	@Override
	public void update() {
		
	}

}
