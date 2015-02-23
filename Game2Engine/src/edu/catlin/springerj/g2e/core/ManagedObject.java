package edu.catlin.springerj.g2e.core;

abstract class ManagedObject {
	private AbstractManager manager;
	private long id = -1;

	public AbstractManager getManager() {
		return manager;
	}

	public AbstractManager getRootManager() {
		if (manager == null) throw new RuntimeException("Does not have a manager.");

		AbstractManager prev = manager, next = manager;
		for (prev = manager; next != null; next = prev.getManager())
			prev = next;
		return prev;
	}

	public void setManager(AbstractManager m) {
		manager = m;
	}
	
	public long getID() {
		if (id == -1) id = Core.allocateID();
		return id;
	}
}
