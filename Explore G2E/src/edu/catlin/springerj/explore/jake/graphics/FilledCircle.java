package edu.catlin.springerj.explore.jake.graphics;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class FilledCircle extends AbstractEntity {

	public FilledCircle() {
		this(0.0d, 0.0d, 100.0d);
	}
	
	public FilledCircle(double x, double y, double r) {
		add(new PositionComponent(new Vector2(x, y)));
		add(new CircleComponent(r));
	}
	
	@Override
	public void initialize() {
		add(new FilledCircleRenderSystem());
	}

	@Override
	public void update() {
		
	}

}
