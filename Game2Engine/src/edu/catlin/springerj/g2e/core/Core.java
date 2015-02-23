package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.catlin.springerj.g2e.exception.CoreException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.thread.TaskThread;

/**
 * Handles basic functions of the game such as loop and events.
 */
public abstract class Core {
	private static AbstractManager manager;
	private static List<TaskThread> threads;
	private static List<Timer> threadtimers;
	private static Random random;

	private static double gcCounter;
	
	private static boolean closeRequested;

	static {
		init();
	}

	public static TaskThread getDefaultTaskThread() {
		if (threads.size() > 0) return threads.get(0);
		throw new CoreException("Default thread has not been created.");
	}
	
	public static TaskThread getTaskThread(int index) {
		return threads.get(index);
	}

	public static Timer getDefaultTimer() {
		return threadtimers.get(0);
	}
	
	public static Timer getTimer(int index) {
		return threadtimers.get(index);
	}

	public static String getResourceFolder() {
		return ".\\resources\\";
	}

	public static AbstractManager getRootManager() {
		return manager;
	}

	public static void init() {
		closeRequested = false;
		threads = new ArrayList<TaskThread>();
		threadtimers = new ArrayList<Timer>();
		threads.add(new TaskThread());
		threadtimers.add(new Timer());
		random = new Random();
		gcCounter = 0;
	}

	public static void initialize(AbstractManager initialManager) {
		Core.manager = initialManager;
	}

	public static boolean isCloseRequested() {
		return closeRequested;
	}

	public static void run() {
		threads.get(0).add(new Task(Task.PRIORITY_VERY_HIGH) {
			public void run() {
				Core.getDefaultTimer().update();
			}
		}, TaskThread.TYPE_CONTINUOUS);
		Core.manager.start();

		while (!closeRequested) {
			threads.get(0).tick();
			gcCounter += Core.getDefaultTimer().getDeltaTime();
			if (gcCounter > 0.1d) {
//				System.gc();
				gcCounter = 0;
			}
		}
		
		Runtime.getRuntime().halt(0);
	}

	public static void setCloseRequested(boolean req) {
		closeRequested = req;
	}

	public static Task task(final Task toStart, int type) {
		getDefaultTaskThread().add(toStart, type);
		return toStart;
	}
	
	public static Task task(final Task toStart, int type, int index) {
		threads.get(index).add(toStart, type);
		return toStart;
	}
	
	public static int createTaskThread() {
		TaskThread thread = new TaskThread();
		threads.add(thread);
		threadtimers.add(new Timer());
		final int id = threads.size()-1;
		thread.add(new Task(Task.PRIORITY_VERY_HIGH) {
			public void run() {
				Core.getTimer(id).update();
			}
		}, TaskThread.TYPE_CONTINUOUS);
		thread.start();
		return id;
	}
	
	public static long allocateID() {
		return Math.abs(random.nextLong());
	}
}
