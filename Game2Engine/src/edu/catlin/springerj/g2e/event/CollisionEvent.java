package edu.catlin.springerj.g2e.event;

import edu.catlin.springerj.g2e.core.AbstractEntity;

public class CollisionEvent extends Event {
	public AbstractEntity a, b;
	
	public CollisionEvent() {
		this(null, null);
	}
	
	public CollisionEvent(AbstractEntity an, AbstractEntity bn) {
		a = an;
		b = bn;
	}
}
