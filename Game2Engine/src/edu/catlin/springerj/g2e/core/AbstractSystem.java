package edu.catlin.springerj.g2e.core;


public abstract class AbstractSystem extends ManagedObject {
	public abstract void initialize();
	public abstract void update();
	
	void background(boolean started) { }
}
