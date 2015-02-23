package edu.catlin.springerj.g2e.event;

public abstract class Event {
	EventListener<?> target;
	protected boolean cancelled;
	
	void setTarget(EventListener<?> t) {
		target = t;
	}
	
	public void setCancelled(boolean c) {
		cancelled = c;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
}
