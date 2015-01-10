package edu.catlin.springerj.g2e.core;

abstract class ManagedObject {
	private AbstractManager manager;

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
}
