package edu.catlin.springerj.explore.jake;

import org.lwjgl.input.Keyboard;

import edu.catlin.springerj.explore.jake.items.Bullet;
import edu.catlin.springerj.explore.jake.items.Grapple;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.utility.Logger;

public class Player extends AbstractEntity {

	public Player() {
		this(0.0d, 0.0d);
	}
	
	public Player(double x, double y) {
		add(new PositionComponent(new Vector2(x, y)));
		add(new RotationComponent(0.0d));
		add(new SpriteComponent("character_walking_right", 8));
	}

	@Override
	public void initialize() {
		add(new SpriteRenderSystem());
		
		final Player thus = this;
		this.getManager().getManager(EventManager.class).register(new EventListener<KeyboardEvent>() {

			@Override
			public void onEvent(KeyboardEvent event) {
				thus.onEvent(event);
			}
			
		});
		
		this.getManager().getManager(EventManager.class).register(new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent event) {
				thus.onEvent(event);
			}
			
		});
		
	}

	@Override
	public void update() {

	}
	
	public void onEvent(KeyboardEvent event) {
		if (event.pressed) {
			this.getComponent(SpriteComponent.class).setSprite("character_walking_right", 8);
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
			this.getComponent(SpriteComponent.class).setSprite("character_idle_left", 8);
		}
	}
	
	public void onEvent(MouseEvent event) {
		if (event.action == MouseEvent.ACTION_PRESS) {
			if (event.button == MouseEvent.BUTTON_MB1) {
				Vector2 playerPosition = this.getComponent(PositionComponent.class).position;
				WindowComponent wc = ((LWJGLManager) Core.getRootManager()).getWindow().getComponent(WindowComponent.class);
				Vector2 velocity = new Vector2((wc.centerx-(wc.width/2))+event.x-playerPosition.x, 
						(wc.centery-(wc.height/2))+event.y-playerPosition.y);
				Core.getRootManager().add(new Bullet(playerPosition, velocity.setLength(300.0d)));
			} else if (event.button == MouseEvent.BUTTON_MB2) {
				Vector2 playerPosition = this.getComponent(PositionComponent.class).position;
				WindowComponent wc = ((LWJGLManager) Core.getRootManager()).getWindow().getComponent(WindowComponent.class);
				Vector2 velocity = new Vector2((wc.centerx-(wc.width/2))+event.x-playerPosition.x, 
						(wc.centery-(wc.height/2))+event.y-playerPosition.y);
				Core.getRootManager().add(new Grapple(playerPosition, velocity.setLength(100.0d)));
			}
		}
	}
}
