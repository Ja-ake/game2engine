package edu.catlin.springerj.g2e.old.events;

public class KeyboardEvent extends Event {
	public boolean pressed;
	public int key;
	
	public KeyboardEvent() { }
	public KeyboardEvent(int k, boolean p) { key = k; pressed = p; }
}
