package edu.catlin.springerj.g2e.lwjgl;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.event.EventManager;

public class LWJGLManager extends AbstractManager {
	private Window window;
	private WindowSystem windowSystem;
	
	public LWJGLManager() {
		
		// Create the window
		window = new Window();
		window.initialize();
		windowSystem = window.getSystem(WindowSystem.class);
		windowSystem.initialize();
		System.out.println("Window has been created successfully.");
		
		// Attach the event manager
		add(new EventManager());
	}

	@Override
	public void initialize() {
		
	}
	
	@Override
	public void run() {
		window.update();
		windowSystem.update();
	}
}
