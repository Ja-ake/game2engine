package edu.catlin.springerj.g2e.old.components;

import edu.catlin.springerj.g2e.old.Component;
import edu.catlin.springerj.g2e.old.events.Event;
import edu.catlin.springerj.g2e.old.events.TickEvent;
import edu.catlin.springerj.g2e.old.math.Vector2;

public class Entity implements Component {
	protected Sprite sprite;
	protected Vector2 position;
	protected Vector2 velocity;

	public Entity() {
		//sprite = new Sprite();
		
		position = new Vector2(0.0f, 0.0f);
		velocity = new Vector2(0.0f, 0.0f);
	}
	
	public Entity(Sprite spr) {
		sprite = spr;
		
		position = new Vector2(0.0f, 0.0f);
		velocity = new Vector2(0.0f, 0.0f);
	}
	
	@Override
	public void update() {
		sprite.update();
	}

	@Override
	public void onEvent(Event e) {
		sprite.onEvent(e);
		
		if (e instanceof TickEvent) {
			position = position.add(velocity);
			//System.out.println(position);
			sprite.setPosition(position);
		}
	}
}
