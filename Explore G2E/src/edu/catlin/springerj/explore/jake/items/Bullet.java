package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;
import edu.catlin.springerj.g2e.movement.VelocityMovementSystem;

public class Bullet extends AbstractEntity {

	public Bullet(Vector2 position, Vector2 velocity) {
		add(new PositionComponent(position));
		add(new VelocityComponent(velocity));
	}
	
	@Override
	public void initialize() {
		add(new VelocityMovementSystem());
	}

	@Override
	public void update() {
		Vector2 pos = get(PositionComponent.class).position;
		Vector2 line = get(VelocityComponent.class).velocity.setLength(10.0d);
		Graphics.drawLine(pos.x, pos.y, pos.x+line.x, pos.y+line.y, 1.0d, 0.3d, 0.3d);
	}

}
