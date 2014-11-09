package edu.catlin.springerj.explore.graphics;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class CircleComponent extends AbstractComponent {
	public double radius;
	
	public CircleComponent() {
		this(1.0d);
	}
	
	public CircleComponent(double r) {
		radius = r;
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
	
}
