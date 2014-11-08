package edu.catlin.springerj.g2e.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import edu.catlin.springerj.g2e.core.AbstractEntity;
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
		e.setTarget(null);
		queue.add(e);
	}
	
	public void fire(Event e, EventListener tar) {
		e.setTarget(tar);
		queue.add(e);
	}

	@Override
	public void run() {
		while (Keyboard.next()) {
			fire(new KeyboardEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
		}
		
		while (Mouse.next()) {
			int action = 0, button = 0, x = Mouse.getEventX(), y = Mouse.getEventY();
			if (Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0) {
				action = MouseEvent.ACTION_MOVE;
				button = 0x10;
			} else if (Mouse.getEventButton() != -1) { if (Mouse.getEventButtonState()) {
				action = MouseEvent.ACTION_PRESS;
				button = Mouse.getEventButton() + 0x10 + 1;
			} else {
				action = MouseEvent.ACTION_RELEASE;
				button = Mouse.getEventButton() + 0x10 + 1;
			} } else {
				action = MouseEvent.ACTION_OTHER;
				button = Mouse.getEventDWheel();
			}
			
			fire(new MouseEvent(action, button, x, y));
		}
		
		//if (listeners.size() == 0) return;
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
				if (event.target == null || event.target == listener) {
					try {
						Method[] methods = listener.getClass()
								.getDeclaredMethods();
						for (Method method : methods) {
							if (method.getName().equals("onEvent")
									&& method.getParameterTypes().length == 1) {
								if (((Class) method.getGenericParameterTypes()[0])
										.getName().equals(
												event.getClass().getName())) {
									method.invoke(listener, event);
								}
							}
						}
					} catch (SecurityException | IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	@Override
	public void initialize() {

	}
}
