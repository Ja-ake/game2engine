package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class GunComponent extends AbstractComponent {

	public static final int TYPE_FIRE = 0x1;
	public static final int TYPE_AIR = 0x2;
	public static final int TYPE_EARTH = 0x3;
	public static final int TYPE_ICE = 0x4;
	
	public double reloadTime;
	public int type;
	public double currentReloadTime;
	public AbstractEntity holder;
	
	public GunComponent(AbstractEntity holder) {
		this(holder, TYPE_FIRE);
	}
	
	public GunComponent(AbstractEntity holder, int t) {
		this(holder, t, 1.0d);
	}
	
	public GunComponent(AbstractEntity h, int t, double reload) {
		currentReloadTime = 0;
		reloadTime = reload;
		type = t;
		holder = h;
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}

}
