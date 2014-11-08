package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class ZoomComponent extends AbstractComponent {
	public double zoom;
	
	public ZoomComponent() {
		this(1.0d);
	}
	
	public ZoomComponent(double z) {
		zoom = z;
	}

	@Override
	public void initialize(AbstractEntity e) {
		
	}
}
