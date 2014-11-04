package edu.catlin.springerj.g2e.object.collision;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.web.CheckEntity;
import edu.catlin.springerj.g2e.web.WebEntity;

public class CircularCollisionWeb extends WebEntity {
	public CircularCollisionWeb() {
		super("CircularCollisionWeb", new CheckEntity() {
			@Override
			public boolean check(AbstractEntity e) {
				if (e != null) {
					try {
						if (e.getComponent(CircularCollisionComponent.class) != null) return true;
					} catch (InvalidComponentException exception) {
						return false;
					}
				}
					
				return false;
			}
		});
	}
}
