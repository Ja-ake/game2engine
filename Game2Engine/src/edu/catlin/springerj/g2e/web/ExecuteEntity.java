package edu.catlin.springerj.g2e.web;

import edu.catlin.springerj.g2e.core.AbstractEntity;

public interface ExecuteEntity {
	public void execute(AbstractEntity ae, Object... args);
}
