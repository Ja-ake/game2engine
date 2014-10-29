package edu.catlin.springerj.g2e.lwjgl.draw;

import java.awt.Font;

import org.newdawn.slick.Color;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.util.FontContainer;
import edu.catlin.springerj.g2e.object.component.PositionComponent;

public class DrawTextSystem extends AbstractSystem {
	private TextComponent tc;
	private PositionComponent pc;
	
	public DrawTextSystem(AbstractEntity ent) {
		tc = ent.getComponent(TextComponent.class);
		pc = ent.getComponent(PositionComponent.class);
	}
	
	@Override
	public void initialize() {
		FontContainer.create();
	}

	@Override
	public void update() {
		FontContainer.drawText(tc.text, "Default", pc.position.x(), pc.position.y(), Color.white);
	}

}
