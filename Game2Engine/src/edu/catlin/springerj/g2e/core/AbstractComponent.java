package edu.catlin.springerj.g2e.core;

/**
 * Contains information for a given component.
 */
public abstract class AbstractComponent extends ManagedObject {
	int id; boolean destroyed = false;
	
	void background(boolean started) { }
	public abstract void initialize(AbstractEntity e);
	public void destroy() {destroyed = true;}
}
