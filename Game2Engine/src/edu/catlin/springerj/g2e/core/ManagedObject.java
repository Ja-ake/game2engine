package edu.catlin.springerj.g2e.core;


abstract class ManagedObject {
	private AbstractManager manager;
	
	public void setManager(AbstractManager m) {
		manager = m;
	}
	
	public AbstractManager getManager() {
		return manager;
	}
	
	abstract void background(boolean started);
}
