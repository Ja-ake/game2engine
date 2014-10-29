package edu.catlin.springerj.g2e.event;

public interface EventListener<T extends Event> {
	public void onEvent(T event);
}