package edu.catlin.springerj.g2e.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import edu.catlin.springerj.g2e.core.AbstractManager;

public class EventManager extends AbstractManager {
	private List<EventListener> listeners;
	private List<Event> queue;

	public EventManager() {
		listeners = new ArrayList<EventListener>();
		queue = new ArrayList<Event>();
	}

	public boolean register(EventListener l) {
		if (listeners == null)
			listeners = new ArrayList<EventListener>();

		if (!listeners.contains(l))
			listeners.add(l);
		else
			return false;
		return true;
	}
	
	public void fire(Event e) {
		queue.add(e);
	}

	@Override
	public void run() {
		while (Keyboard.next()) {
			fire(new KeyboardEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
		}
		
		if (listeners.size() == 0) return;
		for (int i = 0; i < queue.size(); i++) {
			Event event = queue.get(i);
			queue.remove(event); i--;

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
				if (ev.key == Keyboard.KEY_ESCAPE && ev.pressed == true)
					System.exit(0);
			}

			for (EventListener listener : listeners) {
				try {
					Method[] methods = listener.getClass().getDeclaredMethods();
					for (Method method : methods) {
						if (method.getName().equals("onEvent") && method.getParameterTypes().length == 1) {
							if (((Class) method.getGenericParameterTypes()[0]).getName().equals(
									event.getClass().getName())) {
								method.invoke(listener, event);
							}
						}
					}
				} catch (SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public void initialize() {

	}
}
