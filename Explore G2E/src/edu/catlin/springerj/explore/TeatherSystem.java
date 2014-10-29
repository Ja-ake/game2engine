package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.ShapeComponent;
import edu.catlin.springerj.g2e.math.Vector2;

public class TeatherSystem extends AbstractSystem {
	private ShapeComponent sc;
	
	public TeatherSystem(AbstractEntity ent) {
		sc = ent.getComponent(ShapeComponent.class);
		sc.verticies[0] = new Vector2(3.0f, 0.0f);
		sc.verticies[1] = new Vector2(-3.0f, 0.0f);
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		
	}

	public void setTeather(Vector2 pos) {
		sc.verticies[2] = new Vector2(pos.x()-320.0f, pos.y()-3.0f - 240.0f);
		sc.verticies[3] = new Vector2(pos.x()-320.0f, pos.y()+3.0f - 240.0f);
	}
}
