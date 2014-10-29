package edu.catlin.springerj.g2e.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskThread extends Thread {
	private List<Task> continuousTasks;
	private List<Task> noncontinuousTasks;

	public TaskThread() {
		super();
		continuousTasks = new CopyOnWriteArrayList<Task>();
		noncontinuousTasks = new CopyOnWriteArrayList<Task>();
	}

	public void run() {
		while (true) {
			for (int i = 0; i < noncontinuousTasks.size(); i++) {
				Task t = noncontinuousTasks.get(i);
				t.run();
			}
			
			for (int i = 0; i < continuousTasks.size(); i++) {
				Task t = continuousTasks.get(i);
				t.run();
			}
			
			noncontinuousTasks.clear();
		}
	}

	public void add(Task t) {
		if (t.once()) noncontinuousTasks.add(t);
		else continuousTasks.add(t);
	}
}
