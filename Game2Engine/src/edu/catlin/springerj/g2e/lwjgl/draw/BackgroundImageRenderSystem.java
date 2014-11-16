package edu.catlin.springerj.g2e.lwjgl.draw;

import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.utility.Logger;

public class BackgroundImageRenderSystem extends AbstractSystem {
	private ImageComponent ic;
	private ImageRepeatComponent irc;

	@Override
	public void initialize(AbstractEntity e) {
		ic = e.getComponent(ImageComponent.class);
		irc = e.getComponent(ImageRepeatComponent.class);
	}

	@Override
	public void update() {
		glPushMatrix();
        glEnable(GL_TEXTURE_2D);
        ic.image.bind();

		for (int ia=(-irc.xrepeat/2); ia<(irc.xrepeat/2); ia++) {
			for (int ja=(-irc.yrepeat/2); ja<(irc.yrepeat/2); ja++) {
				int i = ia + 1;
				int j = ja + 1;
				glColor3d(1.0d, 1.0d, 1.0d);
				glBegin(GL_QUADS);
				{
					glTexCoord2d(1 - 0, 0);
					glVertex2d(i*ic.image.getImageWidth(), j*ic.image.getImageHeight());

					glTexCoord2d(1 - 0, ic.image.getHeight());
					glVertex2d(i*ic.image.getImageWidth(), j*ic.image.getImageHeight()-ic.image.getImageHeight());

					glTexCoord2d(1 - ic.image.getWidth(), ic.image.getHeight());
					glVertex2d(i*ic.image.getImageWidth()-ic.image.getImageWidth(),
							j*ic.image.getImageHeight()-ic.image.getImageHeight());

					glTexCoord2d(1 - ic.image.getWidth(), 0);
					glVertex2d(i*ic.image.getImageWidth()-ic.image.getImageWidth(), j*ic.image.getImageHeight());

				}
				glEnd();
			}
		}
        glDisable(GL_TEXTURE_2D);
        glPopMatrix();
	}
}
