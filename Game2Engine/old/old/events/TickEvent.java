package edu.catlin.springerj.g2e.old.events;

public class TickEvent extends Event {
	public long current = 0, previous = 0, delta = 0;
	
	public TickEvent() {}
	public TickEvent(long p) { previous = p; }
}
