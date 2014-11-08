package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class CircularCollisionComponent extends AbstractComponent {
	public double radius;
	
	public CircularCollisionComponent() {
		this(1.0f);
	}
	
	public CircularCollisionComponent(double r) {
		radius = r;
	}

    @Override
    public void initialize(AbstractEntity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
