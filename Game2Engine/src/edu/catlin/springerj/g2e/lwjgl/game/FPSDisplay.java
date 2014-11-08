package edu.catlin.springerj.g2e.lwjgl.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.DrawTextSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.TextComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class FPSDisplay extends AbstractEntity {
	@Override
	public void initialize() {
		// Components
		add(new TextComponent("" + 0));
		add(new PositionComponent(new Vector2(-310.0f, 240.0f)));
		//add(new PositionComponent(new Vector2(0.0f, 0.0f)));
		
		// Systems
		add(new DrawTextSystem());
		
		final ActionListener updatefps = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getComponent(TextComponent.class).text = "FPS: " + ((int) (1.0f/Core.getDefaultTimer().getDeltaTime()));
			}
		};
		
		Timer timerstart = new Timer(1000, updatefps);
		timerstart.start();
	}

	@Override
	public void update() {

	}

}