package edu.catlin.springerj.g2e.lwjgl.game;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.DrawTextSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.TextComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.component.PositionComponent;

public class FPSDisplay extends AbstractEntity {

	@Override
	public void initialize() {
		// Components
		add(new TextComponent("" + 0));
		add(new PositionComponent(new Vector2(-270.0f, 230.0f)));
		
		// Systems
		add(new DrawTextSystem(this));
	}

	@Override
	public void update() {
		getComponent(TextComponent.class).text = "FPS: " ;//+ ((int) (1.0f/Core.getDefaultTimer().getDeltaTime()));
	}

}
