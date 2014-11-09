package edu.catlin.springerj.g2e.lwjgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.web.WebManager;

public class LWJGLManager extends AbstractManager {
	private Window window;
	private WindowSystem windowSystem;
	private Map<String, Runnable> rooms;
	
	public LWJGLManager() {
		rooms = new HashMap<String, Runnable>();
		
		// Create the window
		window = new Window();
		window.initialize();
		windowSystem = window.getSystem(WindowSystem.class);
		windowSystem.initialize(window);
		System.out.println("Window has been created successfully.");
	}

	@Override
	public void initialize() {
		
	}
	
	@Override
	public void run() {
		window.update();
		windowSystem.update();
	}
	
	public Window getWindow() {
		return window;
	}
	
	public void addRoom(String name, Runnable code) {
		rooms.put(name, code);
	}
	
	public void setRoom(String name) {
		Core.getDefaultTaskThread().clear();
		this.removeAll();
		
		rooms.get(name).run();
	}
}
