package edu.catlin.springerj.g2e.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.catlin.springerj.g2e.utility.Logger;

public class TaskThread extends Thread {
	public static final int TYPE_CONTINUOUS = 0x01;
	public static final int TYPE_NONCONTINUOUS = 0x02;
	
	private List<Task> continuousTasks;
	private List<Task> noncontinuousTasks;
	private List<Task> continuousBuffer;
	private List<Task> noncontinuousBuffer;

	public TaskThread() {
		super();
		continuousTasks = new ArrayList<Task>();
		noncontinuousTasks = new ArrayList<Task>();
		continuousBuffer = new ArrayList<Task>();
		noncontinuousBuffer = new ArrayList<Task>();
	}

	public void run() {
		while (!isInterrupted()) {
			tick();
		}
	}

	public void tick() {
		swapBuffers();
		sort();
//		System.out.println(noncontinuousTasks.size() + " " + continuousTasks.size());
		for (int i=0; i<noncontinuousTasks.size(); i++) { noncontinuousTasks.get(i).run(); }
		for (int i=0; i<continuousTasks.size(); i++) { continuousTasks.get(i).run(); }
	}

	public Task add(Task t, int type) {
		if (type == TYPE_NONCONTINUOUS) noncontinuousBuffer.add(t);
		else if (type == TYPE_CONTINUOUS) continuousBuffer.add(t);
		else throw new RuntimeException("Task did not have a valid type: " + type + ".");
		
//		if (type == TYPE_CONTINUOUS) System.out.println(continuousBuffer.size());
		
		return t;
	}

	public void remove(int id) {
		for (int i = 0; i < continuousBuffer.size(); i++) {
			Task t = continuousBuffer.get(i);
			if (t != null && t.getID() == id) {
				continuousBuffer.remove(i);
				return;
			}
		}
		
		for (int i = 0; i < noncontinuousBuffer.size(); i++) {
			Task t = noncontinuousBuffer.get(i);
			if (t != null && t.getID() == id) {
				noncontinuousBuffer.remove(i);
				return;
			}
		}
	}

	public void clear() {
		noncontinuousBuffer.clear();
		continuousBuffer.clear();
	}
	
	private void sort() {
		Stack<Task>[] tasks = new Stack[14];
		for (int i=0; i<14; i++) tasks[i] = new Stack<Task>();
		for (int i=0; i<noncontinuousTasks.size(); i++) {
			Task tosort = noncontinuousTasks.get(i);
			tasks[tosort.getPriority()-1].push(tosort);
		}
		for (int i=0; i<continuousTasks.size(); i++) {
			Task tosort = continuousTasks.get(i);
			tasks[tosort.getPriority()+6/*-1+7*/].push(tosort);
		}
		noncontinuousTasks.clear();
		for (int i=0; i<7; i++) {
			while (!tasks[i].isEmpty()) {
				noncontinuousTasks.add(tasks[i].pop());
			}
		}
		continuousTasks.clear();
		for (int i=7; i<14; i++) {
			while (!tasks[i].isEmpty()) {
				continuousTasks.add(tasks[i].pop());
			}
		}
	}
	
	private void swapBuffers() {
		noncontinuousTasks = new ArrayList<Task>(noncontinuousBuffer);noncontinuousBuffer.clear();
		continuousTasks = new ArrayList<Task>(continuousBuffer);
//		Logger.println(continuousTasks.size() + " " + continuousBuffer.size());
	}
}
