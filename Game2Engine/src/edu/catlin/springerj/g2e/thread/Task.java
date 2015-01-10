package edu.catlin.springerj.g2e.thread;

public abstract class Task implements Runnable {
	public static int PRIORITY_VERY_HIGH = 0x1;
	public static int PRIORITY_HIGH = 0x2;
	public static int PRIORITY_ABOVE_NORMAL = 0x3;
	public static int PRIORITY_NORMAL = 0x4;
	public static int PRIORITY_BELOW_NORMAL = 0x5;
	public static int PRIORITY_LOW = 0x6;
	public static int PRIORITY_VERY_LOW = 0x7;

	private int id;
	private int priority;

	public Task() {
		id = (int) Math.floor(Math.random() * ((double) Integer.MAX_VALUE));
		priority = PRIORITY_NORMAL;
	}

	public Task(int p) {
		id = (int) Math.floor(Math.random() * ((double) Integer.MAX_VALUE));
		priority = p;
	}

	public int getID() {
		return id;
	}

	public int getPriority() {
		return priority;
	}

	public abstract void run();

	public void setPriority(int p) {
		priority = p;
	}
}
