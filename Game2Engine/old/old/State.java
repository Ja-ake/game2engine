package edu.catlin.springerj.g2e.old;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.old.events.Event;
import edu.catlin.springerj.g2e.old.events.InitializeEvent;

public abstract class State {
	/**
	 * Stores if a state is active or not.
	 */
	protected boolean active;
	
	/**
	 * List of Components in the state.
	 */
	protected List<Component> components;
	
	public State() {
		components = new ArrayList();
	}
	
	/**
	 * Called when an event is thrown.
	 */
	public abstract void onEvent(Event e);
	
	/**
	 * Sets if the state is active.
	 */
	public void setActive(boolean a) { 
		active = a;
	}
	
	/**
	 * Returns true if the state is active.
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * Add a component to the component list.
	 */
	public void add(Component cmpnt) {
		components.add(cmpnt);
		if (((Controller)StaticData.get("controller")).isInitialized()) cmpnt.onEvent(new InitializeEvent());
	}
	
	/**
	 * Updates states, including components in state.
	 */
	public void update() {
		for (Component comp : components) {
			comp.update();
		}
	}
}
