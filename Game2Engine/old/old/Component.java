package edu.catlin.springerj.g2e.old;

import edu.catlin.springerj.g2e.old.events.Event;

public interface Component {
	/**
	 * Updates the component.
	 */
	public void update();
	
	/**
	 * Called on event throw in State.
	 */
	public void onEvent(Event e);
}
