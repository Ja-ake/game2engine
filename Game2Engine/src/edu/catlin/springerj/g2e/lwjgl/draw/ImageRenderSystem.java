package edu.catlin.springerj.g2e.lwjgl.draw;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.physics.PositionComponent;

public class ImageRenderSystem extends AbstractSystem {
	private PositionComponent pc;
	private ImageComponent ic;

	@Override
	public void initialize(AbstractEntity e) {
		pc = e.getComponent(PositionComponent.class);
		ic = e.getComponent(ImageComponent.class);
	}

	@Override
	public void update() {
		glPushMatrix();
		glEnable(GL_TEXTURE_2D);
		ic.image.bind();
		// Translate twice to rotate at center
		glTranslated(pc.position.x, pc.position.y, 0);
		glTranslated(-ic.image.getWidth() / 2, -ic.image.getHeight() / 2, 0);

		glColor3d(1.0d, 1.0d, 1.0d);
		glBegin(GL_QUADS);
		{
			glTexCoord2d(1 - 0, 0);
			glVertex2d(0, 0);

			glTexCoord2d(1 - 0, ic.image.getHeight());
			glVertex2d(0, -ic.image.getImageHeight());

			glTexCoord2d(1 - ic.image.getWidth(), ic.image.getHeight());
			glVertex2d(-ic.image.getImageWidth(), -ic.image.getImageHeight());

			glTexCoord2d(1 - ic.image.getWidth(), 0);
			glVertex2d(-ic.image.getImageWidth(), 0);

		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		glPopMatrix();

		// System.out.println(pc.position);
		// System.out.println(ic.image.getHeight());
	}

}
