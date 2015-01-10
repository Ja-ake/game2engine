package edu.catlin.springerj.g2e.core;

import edu.catlin.springerj.g2e.thread.Task;

public abstract class AbstractComponent extends ManagedObject {
	Task task;

	public abstract void initialize(AbstractEntity e);
}
