package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.exception.InvalidManagerException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.thread.TaskThread;
import edu.catlin.springerj.g2e.utility.Logger;
import edu.catlin.springerj.g2e.web.WebManager;

/**
 * Handles: - Events - State-wide data
 * 
 */
public abstract class AbstractManager extends ManagedObject {

	public abstract void initialize();

	public abstract void update();

	protected List<AbstractManager> managers;
	protected List<AbstractEntity> entities;

	public AbstractManager() {
		managers = new ArrayList<AbstractManager>();
		entities = new ArrayList<AbstractEntity>();
	}

	public void start() {
		final AbstractManager thus = this;

		// start initialize task
		Core.task(new Task(Task.PRIORITY_VERY_HIGH) {
			@Override
			public void run() {
				thus.initialize();
			}
		}, TaskThread.TYPE_NONCONTINUOUS);

		// start update task
		Core.task(new Task(Task.PRIORITY_VERY_HIGH) {
			@Override
			public void run() {
				thus.update();
			}
		}, TaskThread.TYPE_CONTINUOUS);
	}

	public AbstractManager add(AbstractManager... mans) {
		for (AbstractManager man : mans) {	
			if (managers.contains(man)) throw new InvalidManagerException("Manager has already been created.");
			managers.add(man);
			man.setManager(this);
	
			final AbstractManager fman = man;
	
			// start initialize task
			Core.task(new Task(Task.PRIORITY_VERY_HIGH) {
				@Override
				public void run() {
					fman.initialize();
				}
			}, TaskThread.TYPE_NONCONTINUOUS);
	
			// start update task
			Core.task(new Task(Task.PRIORITY_VERY_HIGH) {
				@Override
				public void run() {
					fman.update();
				}
			}, TaskThread.TYPE_CONTINUOUS);
		}
		return this;
	}

	public AbstractManager add(AbstractEntity... ents) {
		for (AbstractEntity ent : ents) {
			this.add(ent);
		}
		return this;
	}
	
	public AbstractManager add(AbstractEntity ent) {
		entities.add(ent);
		ent.setManager(this);
		
		final AbstractEntity fent = ent;

		// start initialize task
		if (fent.updatetask == null) Core.task(new Task(Task.PRIORITY_HIGH) {
			@Override
			public void run() {
				fent.initialize();
			}
		}, TaskThread.TYPE_NONCONTINUOUS);

		// start update task
		if (fent.updatetask == null) fent.updatetask = Core.task(new Task(Task.PRIORITY_HIGH) {
			@Override
			public void run() {
				fent.update();
			}
		}, TaskThread.TYPE_CONTINUOUS);

		for (int i=0; i<managers.size(); i++) managers.get(i).add(fent);
		return this;
	}

	public AbstractManager remove(AbstractEntity ent) {
		final AbstractEntity fent = ent;
		
		Core.getDefaultTaskThread().remove(ent.updatetask.getID());
		for (AbstractComponent ac : fent.components) {
			if (ac.task != null) Core.getDefaultTaskThread().remove(ac.task.getID()); ac.task = null;
		}

		for (AbstractSystem sc : fent.systems) {
			if (sc.task != null) Core.getDefaultTaskThread().remove(sc.task.getID()); sc.task = null;
		}
		
		Core.task(new Task(Task.PRIORITY_VERY_LOW) {
			@Override
			public void run() {
				fent.components.clear();
				fent.systems.clear();
				entities.remove(fent);
			}
		}, TaskThread.TYPE_NONCONTINUOUS);

		return this;
	}

	public AbstractManager clear() {
		for (int i = 0; i < entities.size(); i++) remove(entities.get(i--));
		for (AbstractManager am : this.managers) am.clear();
		return this;
	}

	public <T extends AbstractManager> T getManager(Class<T> type) {
		for (AbstractManager m : managers) {
			if (m.getClass().equals(type)) { return (T) m; }
		}

		throw new RuntimeException("Invalid manager type.");
	}

	public List<AbstractEntity> getEntities() {
		return entities;
	}

	public List<AbstractEntity> getEntities(Class type) {
		List<AbstractEntity> entitiesoftypet = new ArrayList<AbstractEntity>();
		for (AbstractEntity e : entities) {
			if (e.getClass().equals(type)) { entitiesoftypet.add(e); }
		}

		throw new RuntimeException("Invalid entity type.");
	}
}
