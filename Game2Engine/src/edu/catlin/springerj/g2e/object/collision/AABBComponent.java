package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractComponent;

public class AABBComponent extends AbstractComponent {
	public double width, height;
	
	public AABBComponent() {
		this(0.0f, 0.0f);
	}
	
	public AABBComponent(double wn, double hn) {
		width = wn;
		height = hn;
	}
}
