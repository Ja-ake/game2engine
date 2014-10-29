package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.draw.DrawShapeSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.ShapeComponent;
import edu.catlin.springerj.g2e.math.Color;
import edu.catlin.springerj.g2e.math.Vector2;

public class Teather extends AbstractEntity implements EventListener<MouseEvent> {
	
	@Override
	public void initialize() {
		getManager().get(EventManager.class).register(this);
		
		Vector2[] shape = new Vector2[4];
		shape[0] = shape[1] = shape[2] = shape[3] = new Vector2(0.0f, 0.0f);
		
		// Components
		add(new ShapeComponent(shape, new Color(1.0f, 1.0f, 1.0f)));
		
		// Systems
		add(new TeatherSystem(this));
		add(new DrawShapeSystem(this));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onEvent(MouseEvent event) {
		if (event.action == MouseEvent.ACTION_MOVE) getSystem(TeatherSystem.class).setTeather(new Vector2(event.x, event.y));
	}

}
