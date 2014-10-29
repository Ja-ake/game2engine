package edu.catlin.springerj.g2e.thread;

public abstract class Task implements Runnable {
	private boolean once;
	public abstract void run();
	
	public Task() { once = true; }
	public Task(boolean loop) { once = !loop; }
	
	boolean once() { return once; }
}
