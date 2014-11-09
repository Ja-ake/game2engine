package edu.catlin.springerj.g2e.thread;

public abstract class Task implements Runnable {
	private boolean once;
	private int id;
	public abstract void run();
	
	public Task() { once = true; id = (int) Math.floor(Math.random() * ((double)Integer.MAX_VALUE)); }
	public Task(boolean loop) { once = !loop; id = (int) Math.floor(Math.random() * ((double)Integer.MAX_VALUE)); }
	
	public boolean once() { return once; }
	public int getID() { return id; }
}
