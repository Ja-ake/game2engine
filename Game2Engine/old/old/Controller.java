 package edu.catlin.springerj.g2e.old;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import edu.catlin.springerj.g2e.old.events.Event;
import edu.catlin.springerj.g2e.old.events.InitializeEvent;
import edu.catlin.springerj.g2e.old.events.KeyboardEvent;
import edu.catlin.springerj.g2e.old.events.TickEvent;
import edu.catlin.springerj.g2e.old.other.TextureLoader;

public class Controller {
	/**
	 * A list of all possible states.
	 */
	protected Map<String, State> states;
	
	/**
	 * A list of all events currently queued.
	 */
	protected List<Event> eventQueue;
	
	/**
	 * The current state of the application.
	 */
	protected State currentState;
	
	public Controller() {
		StaticData.add("controller", this);
		StaticData.add("textureloader", new TextureLoader());
		StaticData.add("closerequested", false);
		
		states = new HashMap();
		eventQueue = new ArrayList();
		eventQueue.add(new InitializeEvent());
		eventQueue.add(new TickEvent(System.nanoTime()));
	}
	
	/**
	 * Set the current State. Adds the state to the state map with the name of the class minus "State" in all lowercase. Example: "BigMenuState" turns into "bigmenu".
	 */
	public void setCurrentState(State s) {
		if (!states.containsValue(s)) states.put(s.getClass().getName().replace("State", "").toLowerCase(), s);
		currentState = s;
	}
	
	/**
	 * Sets the current state to a state that exists in the state map.
	 */
	public void setCurrentState(String s) {
		currentState = states.get(s);
	}
	
	/**
	 * Checks if a program should exit.
	 */
	public boolean isCloseRequested() {
		if (isInitialized()) {
			return Display.isCloseRequested() || StaticData.getBoolean("closerequested");
		} else return false;
	}
	
	/**
	 * Checks if all components have been initialized.
	 */
	public boolean isInitialized() {
		for (Event event : eventQueue) {
			if (event instanceof InitializeEvent) return false;
		}
		
		return true;
	}
	
	/**
	 * Captures and calls event functions in active state
	 */
	public void pollEvents() {
		while (Keyboard.next()) eventQueue.add(new KeyboardEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
		
		
		for (int i=0; i<eventQueue.size(); i++) {
			Event event = eventQueue.get(i);
			eventQueue.remove(event);
		
			// TickEvent
			if (event instanceof TickEvent)  {
				((TickEvent) event).current = System.nanoTime();
				((TickEvent) event).delta = ((TickEvent) event).current - ((TickEvent) event).previous;
				TickEvent te = new TickEvent(); te.previous = ((TickEvent) event).current;
				eventQueue.add(te);
			}
		
	    	// KeyboardEvent
			if (event instanceof KeyboardEvent) {
				KeyboardEvent ev = (KeyboardEvent) event;
				if (ev.key == Keyboard.KEY_ESCAPE && ev.pressed == true) StaticData.add("closerequested", true);
			}
	    
	    	// InitializeEvent
	    	if (event instanceof InitializeEvent) {
	    		System.out.println("Initializing.");
	    	}
		
			currentState.onEvent(event);
	    }
	}
	
	/**
	 * Silently updates states.
	 */
	public void update() {
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		
		pollEvents();
		
		for (Map.Entry<String, State> state : states.entrySet()) {
			state.getValue().update();
		}
		
		Display.update();
	}
}
