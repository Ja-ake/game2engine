package edu.catlin.springerj.g2e.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.thread.Task;

public class EventManager extends AbstractManager {

	private List<EventListener> listeners;
	private List<EventListener> highPriorityListeners;
	private List<Event> queue;

	public EventManager() {
		super(Task.PRIORITY_VERY_LOW, 0);

		listeners = new ArrayList<EventListener>();
		highPriorityListeners = new ArrayList<EventListener>();
		queue = new ArrayList<Event>();
	}

	@Override
	public AbstractManager clear() {
		super.clear();
		listeners.clear();
		highPriorityListeners.clear();
		queue.clear();
		
		return this;
	}

	public void fire(Event e) {
		if (e == null) {
			throw new RuntimeException("You cannot fire a null event.");
		}
		e.setTarget(null);
		queue.add(e);
	}

	public void fire(Event e, EventListener tar) {
		if (e == null) {
			throw new RuntimeException("You cannot fire a null event.");
		}
		e.setTarget(tar);
		queue.add(e);
	}

	@Override
	public void initialize() {

	}

	public boolean register(EventListener l) {
		if (listeners == null) {
			listeners = new ArrayList<EventListener>();
		}

		if (!listeners.contains(l)) {
			listeners.add(l);
		} else {
			return false;
		}
		return true;
	}

	public boolean registerHighPriority(EventListener l) {
		if (highPriorityListeners == null) {
			highPriorityListeners = new ArrayList<EventListener>();
		}

		if (!highPriorityListeners.contains(l)) {
			highPriorityListeners.add(l);
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public void update() {
		while (Keyboard.next()) {
			fire(new KeyboardEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
		}

		while (Mouse.next()) {
			int action = 0, button = 0, x = Mouse.getEventX(), y = Mouse.getEventY();
			if (Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0) {
				action = MouseEvent.ACTION_MOVE;
				button = 0x10;
			} else if (Mouse.getEventButton() != -1) {
				if (Mouse.getEventButtonState()) {
					action = MouseEvent.ACTION_PRESS;
					button = Mouse.getEventButton() + 0x10 + 1;
				} else {
					action = MouseEvent.ACTION_RELEASE;
					button = Mouse.getEventButton() + 0x10 + 1;
				}
			} else {
				action = MouseEvent.ACTION_OTHER;
				button = Mouse.getEventDWheel();
			}

			fire(new MouseEvent(action, button, x, y));
		}

		// if (listeners.size() == 0) return;
		EVENTLOOP:
		for (int i = 0; i < queue.size(); i++) {
			Event event = queue.get(i);
			queue.remove(event);
			i--;

			// TickEvent
			if (event instanceof TickEvent) {
				((TickEvent) event).current = System.nanoTime();
				((TickEvent) event).delta = ((TickEvent) event).current - ((TickEvent) event).previous;
				TickEvent te = new TickEvent();
				te.previous = ((TickEvent) event).current;
				queue.add(te);
			}

			// KeyboardEvent
			if (event instanceof KeyboardEvent) {
				KeyboardEvent ev = (KeyboardEvent) event;
				if (ev.key == Keyboard.KEY_ESCAPE && ev.pressed == true) {
					Core.setCloseRequested(true);
					System.exit(0);
				}
			}

			for (int j = 0; j < highPriorityListeners.size(); j++) {
				EventListener listener = highPriorityListeners.get(j);
				if (event == null) { continue EVENTLOOP; }
				if (event.target == null || event.target == listener) {
					try {
						Method[] methods = listener.getClass().getDeclaredMethods();
						for (Method method : methods) {
							if (method.getName().equals("onEvent") && method.getParameterTypes().length == 1) {
								if (((Class) method.getGenericParameterTypes()[0]).getName().equals(event.getClass().getName())) {
									method.setAccessible(true);
									method.invoke(listener, event);
								}
							}
						}
					} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			if (!event.isCancelled()) for (int j = 0; j < listeners.size(); j++) {
				EventListener listener = listeners.get(j);
				if (event == null) { continue EVENTLOOP; }
				if (event.target == null || event.target == listener) {
					try {
						Method[] methods = listener.getClass().getDeclaredMethods();
						for (Method method : methods) {
							if (method.getName().equals("onEvent") && method.getParameterTypes().length == 1) {
								if (((Class) method.getGenericParameterTypes()[0]).getName().equals(event.getClass().getName())) {
									method.setAccessible(true);
									method.invoke(listener, event);
								}
							}
						}
					} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
