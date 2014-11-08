package edu.catlin.springerj.g2e.lwjgl;

import edu.catlin.springerj.g2e.core.AbstractEntity;

public class Window extends AbstractEntity {
	public Window() {
		add(new WindowComponent("Game", 1200, 800));
		add(new WindowSystem());
	}
	
	@Override
	public void initialize() {
		
	}
	
	@Override
	public void update() {
		
	}
}
