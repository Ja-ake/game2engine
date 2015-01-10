package edu.catlin.springerj.g2e.event;

public abstract class Event {
	EventListener<?> target;

	void setTarget(EventListener<?> t) {
		target = t;
	}
}
