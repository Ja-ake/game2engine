package edu.catlin.springerj.g2e.lwjgl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.thread.Task;
import edu.catlin.springerj.g2e.web.WebManager;

public class LWJGLManager extends AbstractManager {
	private Window window;
	private WindowSystem windowSystem;
	private Map<String, Runnable> rooms;
	private List<DrawCommand> drawCommands;

	public LWJGLManager() {
		super(Task.PRIORITY_VERY_LOW, 0);
		
		rooms = new HashMap<String, Runnable>();

		// Create the window
		window = new Window();
		window.initialize();
		windowSystem = window.getSystem(WindowSystem.class);
		windowSystem.initialize(window);
		drawCommands = Collections.synchronizedList(new ArrayList<DrawCommand>());
		
		System.out.println("Window has been created successfully.");
	}

	public void addRoom(String name, Runnable code) {
		rooms.put(name, code);
	}

	public Window getWindow() {
		return window;
	}

	@Override
	public void initialize() {

	}

	public void setRoom(String name) {
		Core.getDefaultTaskThread().clear();
		this.clear();

		rooms.get(name).run();
	}
	
	public synchronized void draw(DrawCommand dc) {
		synchronized (drawCommands) {
			drawCommands.add(dc);
		}
	}

	@Override
	public void update() {
		synchronized (drawCommands) {
			drawCommands.sort(new Comparator<DrawCommand>() {
				@Override
				public int compare(DrawCommand one, DrawCommand other) {
					if (one.layer > other.layer) return 1;
					if (one.layer < other.layer) return -1;
					return 0;
				}
			});
			
			for (DrawCommand dc : drawCommands) dc.draw();
			drawCommands.clear();
		}
		
		window.update();
		windowSystem.update();
	}
}
