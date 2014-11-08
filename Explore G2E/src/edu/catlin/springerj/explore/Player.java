package edu.catlin.springerj.explore;

import org.lwjgl.input.Keyboard;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class Player extends AbstractEntity implements
		EventListener<KeyboardEvent> {

	public Player() {
		add(new PositionComponent(new Vector2(100.0f, 100.0f)));
	}

	@Override
	public void initialize() {
		add(new SpriteComponent());
		add(new SpriteRenderSystem(this, "sprite\\block_red"));
		this.getManager().getManager(EventManager.class).register(this);
	}

	@Override
	public void update() {

	}

	@Override
	public void onEvent(KeyboardEvent event) {
		if (event.pressed) {
			if (event.key == Keyboard.KEY_W) {
				this.getComponent(PositionComponent.class).position = this
						.getComponent(PositionComponent.class).position
						.add(new Vector2(0.0d, 25.0d));
			} else if (event.key == Keyboard.KEY_S) {
				this.getComponent(PositionComponent.class).position = this
						.getComponent(PositionComponent.class).position
						.add(new Vector2(0.0d, -25.0d));
			} else if (event.key == Keyboard.KEY_A) {
				this.getComponent(PositionComponent.class).position = this
						.getComponent(PositionComponent.class).position
						.add(new Vector2(-25.0d, 0.0d));
			} else if (event.key == Keyboard.KEY_D) {
				this.getComponent(PositionComponent.class).position = this
						.getComponent(PositionComponent.class).position
						.add(new Vector2(25.0d, 0.0d));
			}
		} else {

		}
	}
}
