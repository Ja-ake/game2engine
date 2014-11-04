package edu.catlin.springerj.explore;

import org.lwjgl.input.Keyboard;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;

public class Player extends AbstractEntity implements EventListener<KeyboardEvent> {	
	@Override
	public void initialize() {
		add(new SpriteComponent());
		add(new SpriteRenderSystem(this, "boulder"));
		this.getManager().getManager(EventManager.class).register(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onEvent(KeyboardEvent event) {
		if (event.pressed) {
			if (event.key == Keyboard.KEY_W) {
				this.getSystem(SpriteRenderSystem.class).setPositionRelative(0.0f, 10.0f);
			}
		} else {
			
		}
	}
}
