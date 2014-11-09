package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.exception.InvalidSystemException;
import edu.catlin.springerj.g2e.thread.Task;

public abstract class AbstractEntity extends ManagedObject {
	public abstract void initialize();
	public abstract void update();

	Task updatetask;
	
	List<AbstractComponent> components;
	List<AbstractSystem> systems;

	public AbstractEntity() {
		components = new ArrayList<AbstractComponent>();
		systems = new ArrayList<AbstractSystem>();
	}
	
	void background(boolean started) { if (started) for (AbstractSystem as : systems) as.update(); }

	/**
	 * Adds a list of components to the entity.
	 */
	public boolean add(AbstractComponent... cs) {
		boolean r = true;

		final AbstractEntity thus = this;
		for (AbstractComponent c : cs) {
			String cName = c.getClass().getName();
			boolean contains = false;
			for (AbstractComponent check : components) {
				if (check.getClass().getName() == cName) {
					contains = true;
					r = false;
				}
			}
			if (!contains) {
				components.add(c);
				c.setManager(this.getManager());
				final AbstractComponent fc = c;
				Core.task(new Task() {
					public void run() {
						fc.initialize(thus);
					}
				});
				
				//c.initialize(thus);
			}
		}

		return r;
	}

	/**
	 * Adds a list of systems to the entity.
	 */
	public boolean add(AbstractSystem... ss) {
		boolean r = true;

		final AbstractEntity thus = this;
		for (AbstractSystem s : ss) {
			String cName = s.getClass().getName();
			boolean contains = false;
			for (AbstractSystem check : systems) {
				if (check.getClass().getName() == cName) {
					contains = true;
					r = false;
				}
			}
			if (!contains) {
				systems.add(s);
				s.setManager(this.getManager());
				final AbstractSystem fs = s;
				Core.task(new Task() {
					public void run() {
						fs.initialize(thus);
					}
				});
			}
		}

		return r;
	}
	
	public <E extends AbstractComponent>E getComponent(Class<E> cc) {
		for (AbstractComponent component : components) {
			if (component.getClass().equals(cc)) return (E) component;
		}
		
		throw new InvalidComponentException("Could not find an instance of " + cc.getName() + ".");
	}
	
	public <E extends AbstractComponent>E getComponent(String cn) {
		for (AbstractComponent component : components) {
			if (component.getClass().getSimpleName().equals(cn) || component.getClass().getName().equals(cn)) return (E) component;
		}
		
		throw new InvalidComponentException("Could not find an instance of " + cn + ".");
	}
	
	public <E extends AbstractSystem>E getSystem(Class<E> cc) {
		for (AbstractSystem system : systems) {
			if (system.getClass().equals(cc)) return (E) system;
		}
		
		throw new InvalidSystemException("Could not find an instance of " + cc.getName() + ".");
	}
	
	public <E extends AbstractSystem>E getSystem(String cn) {
		for (AbstractSystem system : systems) {
			if (system.getClass().getSimpleName().equals(cn) || system.getClass().getName().equals(cn)) return (E) system;
		}
		
		throw new InvalidSystemException("Could not find an instance of " + cn + ".");
	}
	
	public <E>E get(String cn) {
		for (AbstractComponent component : components) {
			if (component.getClass().getSimpleName().equals(cn) || component.getClass().getName().equals(cn)) return (E) component;
		}
		
		for (AbstractSystem system : systems) {
			if (system.getClass().getSimpleName().equals(cn) || system.getClass().getName().equals(cn)) return (E) system;
		}
		
		throw new InvalidComponentException("Could not find an instance of " + cn + ".");
	}
	
	public <E>E get(Class<E> cc) {
		for (AbstractComponent component : components) {
			if (component.getClass().equals(cc)) return (E) component;
		}
		
		for (AbstractSystem system : systems) {
			if (system.getClass().equals(cc)) return (E) system;
		}
		
		if (cc.isInstance(AbstractSystem.class)) throw new InvalidSystemException("Could not find an instance of " + cc.getName() + ".");
		else throw new InvalidComponentException("Could not find an instance of " + cc.getName() + ".");
	}
}
