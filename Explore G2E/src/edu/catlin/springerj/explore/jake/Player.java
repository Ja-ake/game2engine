package edu.catlin.springerj.explore.jake;

import org.lwjgl.input.Keyboard;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;

public class Player extends AbstractEntity implements
		EventListener<KeyboardEvent> {

	public Player() {
		this(0.0d, 0.0d);
	}
	
	public Player(double x, double y) {
		add(new PositionComponent(new Vector2(x, y)));
		add(new RotationComponent(0.0d));
		add(new SpriteComponent("sprite\\character_walking", 8));
	}

	@Override
	public void initialize() {
		add(new SpriteRenderSystem());
		this.getManager().getManager(EventManager.class).register(this);
	}

	@Override
	public void update() {

	}

	@Override
	public void onEvent(KeyboardEvent event) {
		if (event.pressed) {
			this.getComponent(SpriteComponent.class).setSprite("sprite\\character_walking");
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
			this.getComponent(SpriteComponent.class).setSprite("sprite\\character_standing");
		}
	}
	
//	@Override
//	public void onEvent(MouseEvent event) {
//		
//	}
}
