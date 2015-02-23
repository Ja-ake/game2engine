package edu.catlin.springerj.g2e.core;

import edu.catlin.springerj.g2e.thread.Task;

public abstract class AbstractSystem extends ManagedObject {
	Task task;
	int taskThread;

	public abstract void initialize(AbstractEntity e);

	public abstract void update();
}
