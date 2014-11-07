package edu.catlin.springerj.g2e.core;


abstract class ManagedObject {
	private AbstractManager manager;
	
	public void setManager(AbstractManager m) {
		manager = m;
	}
	
	public AbstractManager getManager() {
		return manager;
	}
	
	public AbstractManager getRootManager() {
		AbstractManager prev = manager, next = manager;
		for (prev = manager; next != null; next = prev.getManager()) prev = next;
		return prev;
	}
	
	abstract void background(boolean started);
}
