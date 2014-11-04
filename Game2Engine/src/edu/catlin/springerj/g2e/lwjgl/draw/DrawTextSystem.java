package edu.catlin.springerj.g2e.lwjgl.draw;

import org.newdawn.slick.Color;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.util.FontContainer;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;

public class DrawTextSystem extends AbstractSystem {
	private TextComponent tc;
	private PositionComponent pc;
	
	@Override
	public void initialize(AbstractEntity ent) {
		tc = ent.getComponent(TextComponent.class);
		pc = ent.getComponent(PositionComponent.class);
		FontContainer.create();
	}

	@Override
	public void update() {
		FontContainer.drawText(tc.text, "Default", pc.position.x(), pc.position.y(), Color.black);
	}

}
