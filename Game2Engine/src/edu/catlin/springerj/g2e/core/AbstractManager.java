package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.exception.InvalidManagerException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.web.WebManager;

/**
 * Handles:
 * - Events
 * - State-wide data
 * 
 */
public abstract class AbstractManager extends ManagedObject {
	public abstract void initialize(); protected boolean initialized;
	public abstract void run();
	
	private List<AbstractManager> 	managers;
	private List<AbstractEntity>	entities;	
	
	public AbstractManager() {
		managers = new ArrayList<AbstractManager>();
		entities = new ArrayList<AbstractEntity>();
		
		initialized = false;
	}
	
	void background(boolean started) {
		for (ManagedObject mo : managers) {
			mo.background(started);
		}
		
		for (ManagedObject mo : entities) {
			mo.background(started);
		}
	}
	
	public void start() {
		initialized = true;
		initialize();
		
		final AbstractManager thus = this;
		Core.task(new Task() {
			public void run() {
				//for (AbstractManager m : managers) m.initialize();
				//for (AbstractEntity e : entities) e.initialize();
				thus.background(false);
			}
		});
		
		Core.task(new Task(true) {
			public void run() {
				if (Core.isCloseRequested()) System.exit(0);
				
				thus.run();
				thus.background(true);
			}
		});
	}
	
	public AbstractManager add(AbstractManager man) {
		if (managers.contains(man)) throw new InvalidManagerException("Manager has already been created.");
		managers.add(man);
		man.setManager(this);
		//if (initialized) man.initialize();
		final AbstractManager fm = man;
		Core.task(new Task() {
			@Override
			public void run() {
				fm.initialize();
			}
		});
		
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
		if (initialized) ent.initialize();
		final AbstractEntity et = ent;
		Core.task(new Task(true) {
			@Override
			public void run() {
				et.update();
			}
		});
		
		for (AbstractManager m : managers) {
			m.add(ent);
		}
		
		return this;
	}
	
	public <T extends AbstractManager>T getManager(Class<T> type) {
		for (AbstractManager m : managers) {
			if (m.getClass().equals(type)) return (T) m;
		}
		
		throw new RuntimeException("Invalid manager type.");
	}
	
	public List<AbstractEntity> getEntities() {
		return entities;
	}
}
