package edu.catlin.springerj.g2e.lwjgl.draw;

import static org.lwjgl.opengl.GL11.*;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.object.component.PositionComponent;

public class DrawShapeSystem extends AbstractSystem {
	private ShapeComponent sc;
	private PositionComponent pc;

	public DrawShapeSystem(AbstractEntity ent) {
		sc = ent.getComponent(ShapeComponent.class);
		try {
			pc = ent.getComponent(PositionComponent.class);
		} catch (InvalidComponentException e) {
			pc = null;
		}
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void update() {
		glPushMatrix();
		if (pc != null) glTranslated(-pc.position.x(), -pc.position.y(), 0.0f);
		glBegin(GL_TRIANGLES);
		glColor3f(sc.color.red, sc.color.green, sc.color.blue);
		for (int i=2; i<sc.verticies.length; i++) {
			glVertex2d(sc.verticies[ 0 ].x(), sc.verticies[ 0 ].y());
			glVertex2d(sc.verticies[ i ].x(), sc.verticies[ i ].y());
			glVertex2d(sc.verticies[i-1].x(), sc.verticies[i-1].y());
		}
		glEnd();
		glPopMatrix();
	}
	
}
