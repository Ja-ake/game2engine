package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.exception.InvalidSystemException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.thread.TaskThread;

@SuppressWarnings("unchecked")
public abstract class AbstractEntity extends ManagedObject {
	Task updatetask;
	List<AbstractComponent> components;
	List<AbstractSystem> systems;
	int taskThread;
	
	public AbstractEntity() {
		components = new ArrayList<AbstractComponent>();
		systems = new ArrayList<AbstractSystem>();
	}

	/**
	 * Adds a list of components to the entity.
	 */
	public AbstractEntity add(AbstractComponent... cs) {
		final AbstractEntity thus = this;
		for (AbstractComponent c : cs) {
			String cName = c.getClass().getName();
			boolean contains = false;
			
			c.taskThread = taskThread;

			// check contents
			for (AbstractComponent check : components) {
				if (check.getClass().getName() == cName) {
					contains = true;
				}
			}

			if (!contains) {
				components.add(c);
				c.setManager(this.getManager());

				final AbstractComponent fc = c;
				Core.task(new Task(Task.PRIORITY_HIGH) {
					public void run() {
						fc.initialize(thus);
					}
				}, TaskThread.TYPE_NONCONTINUOUS, taskThread);
			}
		}

		return this;
	}

	/**
	 * Adds a list of systems to the entity.
	 */
	public AbstractEntity add(AbstractSystem... ss) {
		final AbstractEntity thus = this;
		for (AbstractSystem s : ss) {
			String cName = s.getClass().getName();
			boolean contains = false;
			
			s.taskThread = taskThread;

			// check contents
			for (AbstractSystem check : systems) {
				if (check.getClass().getName() == cName) {
					contains = true;
				}
			}

			if (!contains) {
				systems.add(s);
				s.setManager(this.getManager());

				final AbstractSystem fs = s;
				Core.task(new Task(Task.PRIORITY_ABOVE_NORMAL) {
					public void run() {
						fs.initialize(thus);
					}
				}, TaskThread.TYPE_NONCONTINUOUS, taskThread);

				s.task = Core.task(new Task(Task.PRIORITY_ABOVE_NORMAL) {
					public void run() {
						fs.update();
					}
				}, TaskThread.TYPE_CONTINUOUS, taskThread);
			}
		}

		return this;
	}

	public boolean contains(Class cc) {
		for (AbstractComponent component : components) {
			if (component.getClass().equals(cc)) return true;
		}

		for (AbstractSystem system : systems) {
			if (system.getClass().equals(cc)) return true;
		}

		return false;
	}

	public <E> E get(Class<E> cc) {
		for (AbstractComponent component : components) {
			if (component.getClass().equals(cc)) return (E) component;
		}

		for (AbstractSystem system : systems) {
			if (system.getClass().equals(cc)) return (E) system;
		}

		if (cc.isInstance(AbstractSystem.class)) throw new InvalidSystemException("Could not find an instance of " + cc.getName() + ".");
		else throw new InvalidComponentException("Could not find an instance of " + cc.getName() + ".");
	}

	public <E> E get(String cn) {
		for (AbstractComponent component : components) {
			if (component.getClass().getSimpleName().equals(cn) || component.getClass().getName().equals(cn)) return (E) component;
		}

		for (AbstractSystem system : systems) {
			if (system.getClass().getSimpleName().equals(cn) || system.getClass().getName().equals(cn)) return (E) system;
		}

		throw new InvalidComponentException("Could not find an instance of " + cn + ".");
	}

	public <E extends AbstractComponent> E getComponent(Class<E> cc) {
		for (AbstractComponent component : components) {
			if (component.getClass().equals(cc)) return (E) component;
		}

		throw new InvalidComponentException("Could not find an instance of " + cc.getName() + ".");
	}

	public <E extends AbstractComponent> E getComponent(String cn) {
		for (AbstractComponent component : components) {
			if (component.getClass().getSimpleName().equals(cn) || component.getClass().getName().equals(cn)) return (E) component;
		}

		throw new InvalidComponentException("Could not find an instance of " + cn + ".");
	}

	public <E extends AbstractSystem> E getSystem(Class<E> cc) {
		for (AbstractSystem system : systems) {
			if (system.getClass().equals(cc)) return (E) system;
		}

		throw new InvalidSystemException("Could not find an instance of " + cc.getName() + ".");
	}

	public <E extends AbstractSystem> E getSystem(String cn) {
		for (AbstractSystem system : systems) {
			if (system.getClass().getSimpleName().equals(cn) || system.getClass().getName().equals(cn)) return (E) system;
		}

		throw new InvalidSystemException("Could not find an instance of " + cn + ".");
	}

	public abstract void initialize();

	public abstract void update();
}
