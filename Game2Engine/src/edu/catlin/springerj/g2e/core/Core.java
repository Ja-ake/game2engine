package edu.catlin.springerj.g2e.core;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.exception.CoreException;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.thread.TaskThread;

/**
 * Handles basic functions of the game such as loop and events.
 */
public abstract class Core {
	private static AbstractManager manager;
	private static List<TaskThread> threads;
	private static List<TaskThread> tostart;
	private static List<Timer> threadtimers;
	
	private static boolean closeRequested;

	static {
		closeRequested = false;
		threads = new ArrayList<TaskThread>();
		tostart = new ArrayList<TaskThread>();
		threadtimers = new ArrayList<Timer>();
		threads.add(new TaskThread());
		threadtimers.add(new Timer());
	}

	public static void initialize(AbstractManager initialManager) {
		Core.manager = initialManager;
		Core.manager.start();
	}
	
	public static void run() {
//		threads.get(0).add(new Task(true) {
//			public void run() {
//				Core.getDefaultTimer().update();
//			}
//		});
		
		while (!closeRequested) {
			for (TaskThread t : tostart) {
				if (!t.isAlive()) {
					t.start();
					final Timer ty = new Timer();
					threads.add(t);
					threadtimers.add(ty);
					t.add(new Task(true) {
						public void run() {
							ty.update();
						}
					});
				}
			}
			
			Core.getDefaultTimer().update();
			threads.get(0).run();
		}
	}
	
	public static Thread task(final Task toStart) {
		getDefaultTaskThread().add(toStart);
		return getDefaultTaskThread();
	}
	
	public static void setCloseRequested(boolean req) {
		closeRequested = req;
	}
	
	public static boolean isCloseRequested() {
		return closeRequested;
	}
	
	public static TaskThread getDefaultTaskThread() {
		if (threads.size() > 0) return threads.get(0);
		throw new CoreException("Default thread has not been created.");
	}
	
	public static Timer getDefaultTimer() {
		return threadtimers.get(0);
	}
	
	public static String getResourceFolder() {
		return ".\\resources\\";
	}
	
	public static AbstractManager getRootManager() {
		return manager;
	}
}
