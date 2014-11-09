package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class BulletRechargeComponent extends AbstractComponent {
	public double rechargeTime;
	public double currentRecharge;
	
	public BulletRechargeComponent() {
		this(1.0d);
	}
	
	public BulletRechargeComponent(double r) {
		rechargeTime = r;
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
}
