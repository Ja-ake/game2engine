package edu.catlin.springerj.explore.jake;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.draw.ImageComponent;
import edu.catlin.springerj.g2e.lwjgl.draw.ImageRenderSystem;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class Tile extends AbstractEntity {
	
	public Tile(Texture t, int x, int y) {
		// Components
		add(new ImageComponent(t));
		add(new PositionComponent(new Vector2(x, y)));
	}
	
	@Override
	public void initialize() {
		// Systems
		add(new ImageRenderSystem());
	}

	@Override
	public void update() {
		
	}
}
