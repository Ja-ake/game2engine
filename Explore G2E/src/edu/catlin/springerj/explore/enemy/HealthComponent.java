package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class HealthComponent extends AbstractComponent {

	double health;
	
	public HealthComponent() {
		this(10.0d);
	}
	
	public HealthComponent(double h) {
		health = h;
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}

}
