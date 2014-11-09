package edu.catlin.springerj.g2e.thread;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskThread extends Thread {
	private List<Task> continuousTasks;
	private Queue<Task> noncontinuousTasks;

	public TaskThread() {
		super();
		continuousTasks = new CopyOnWriteArrayList<Task>();
		noncontinuousTasks = new ConcurrentLinkedQueue<Task>();
	}

	public int ssa = 0;
	public void run() {
		while (true) {			
			for (int i = 0; i < noncontinuousTasks.size(); i++) {
				noncontinuousTasks.remove().run();
			}
			
			for (int i = 0; i < continuousTasks.size(); i++) {
				Task t = continuousTasks.get(i);
				t.run();
			}
		}
	}

	public Task add(Task t) {
		if (t.once()) noncontinuousTasks.add(t);
		else continuousTasks.add(t);
		
		return t;
	}
	
	public void remove(int id) {
		for (int i=0; i<continuousTasks.size(); i++) { 
			if (continuousTasks.get(i).getID() == id) {
				continuousTasks.remove(i);
				return;
			}
		}
	}
	
	public void clear() {
		noncontinuousTasks.clear();
		continuousTasks.clear();
	}
}
