package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.exception.InvalidManagerException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.utility.Logger;
import edu.catlin.springerj.g2e.web.WebManager;

/**
 * Handles: - Events - State-wide data
 * 
 */
public abstract class AbstractManager extends ManagedObject {

	public abstract void initialize();

	protected boolean initialized;

	public abstract void run();

	protected List<AbstractManager> managers;
	protected List<AbstractEntity> entities;

	public AbstractManager() {
		managers = new ArrayList<AbstractManager>();
		entities = new ArrayList<AbstractEntity>();

		initialized = false;
	}

	void background(boolean started) {
		for (int i = 0; i < managers.size(); i++) {
			managers.get(i).background(started);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).background(started);
		}
	}

	public void start() {
		initialized = true;
		initialize();

		final AbstractManager thus = this;
		Core.task(new Task() {
			public void run() {
				thus.background(false);
			}
		});

		Core.task(new Task(true) {
			public void run() {
				if (Core.isCloseRequested()) {
					System.exit(0);
				}

				thus.run();
				thus.background(true);
			}
		});
	}

	public AbstractManager add(AbstractManager man) {
		if (managers.contains(man)) {
			throw new InvalidManagerException(
					"Manager has already been created.");
		}
		managers.add(man);
		man.setManager(this);
		man.initialize();
		final AbstractManager fm = man;
		// Core.task(new Task() {
		// @Override
		// public void run() {
		// fm.initialize();
		// }
		// });

		Core.task(new Task(true) {
			@Override
			public void run() {
				fm.run();
			}
		});

		return this;
	}

	public AbstractManager add(AbstractEntity ent) {
		entities.add(ent);
		ent.setManager(this);
		final AbstractEntity et = ent;
		ent.initialize();
		ent.updatetask = new Task(true) {
			@Override
			public void run() {
				et.update();
			}
		};
		Core.task(ent.updatetask);

		for (AbstractManager m : managers) {
			m.autoAdd(ent);
		}

		return this;
	}

	public AbstractManager remove(AbstractEntity ent) {
		for (AbstractComponent ac : ent.components) {
			if (!ac.destroyed)
				ac.destroy();
			if (ac.destroyed)
				Core.getDefaultTaskThread().remove(ac.id);
		}

		for (AbstractSystem sc : ent.systems) {
			if (!sc.destroyed)
				sc.destroy();
			if (sc.destroyed)
				Core.getDefaultTaskThread().remove(sc.id);
		}

		for (int i = 0; i < ent.components.size(); i++)
			if (ent.components.get(i).destroyed)
				ent.components.remove(i--);
		for (int i = 0; i < ent.systems.size(); i++)
			if (ent.systems.get(i).destroyed)
				ent.systems.remove(i--);

		Core.getDefaultTaskThread().remove(ent.updatetask.getID());
		entities.remove(ent);

		return this;
	}

	public AbstractManager removeAll() {
		entities.clear();
		this.start();
		for (AbstractManager am : this.managers)
			am.removeAll();
		//managers.clear();
		return this;
	}

	public AbstractManager autoAdd(AbstractEntity ent) {
		entities.add(ent);
		return this;
	}

	public <T extends AbstractManager> T getManager(Class<T> type) {
		for (AbstractManager m : managers) {
			if (m.getClass().equals(type)) {
				return (T) m;
			}
		}

		throw new RuntimeException("Invalid manager type.");
	}

	public List<AbstractEntity> getEntities() {
		return entities;
	}

	public <T extends AbstractEntity> T getEntity(Class<T> type) {
		for (AbstractEntity m : entities) {
			if (m.getClass().equals(type)) {
				return (T) m;
			}
		}

		throw new RuntimeException("Invalid entity type.");
	}
}
