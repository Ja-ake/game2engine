package edu.catlin.springerj.g2e.lwjgl;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.IOException;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.lwjgl.util.TextureLoader;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class SpriteRenderSystem extends AbstractSystem {
	private SpriteComponent sc;
	private PositionComponent pc;
	
	public SpriteRenderSystem(AbstractEntity e, String image) {
		sc = e.getComponent(SpriteComponent.class);
		try {
			pc = e.getComponent(PositionComponent.class);
		} catch (InvalidComponentException exception) {
			pc = null;
		}
		
		try {
			sc.textures = TextureLoader.getTexture(Core.getResourceFolder() + image + ".png", 1);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
	}
	
	@Override
	public void initialize(AbstractEntity ent) {
		
	}

	@Override
	public void update() {
		if (pc != null) { setPosition(pc.position); }
		
		glPushMatrix();
        glEnable(GL_TEXTURE_2D);
        sc.getTexture(0).bind();
        //Translate twice to rotate at center
        glTranslated(sc.x, sc.y, 0);
        glTranslated(-sc.getWidth() / 2, -sc.getHeight() / 2, 0);

        glColor3d(1, 1, 1);
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(0, sc.getHeight()); //Height reversed because sprite y axis upside-down
            glTexCoord2d(0, sc.getTexture(0).getHeight());
            glVertex2d(0, 0);
            glTexCoord2d(sc.getTexture(0).getWidth(), sc.getTexture(0).getHeight());
            glVertex2d(sc.getWidth(), 0);
            glTexCoord2d(sc.getTexture(0).getWidth(), 0);
            glVertex2d(sc.getWidth(), sc.getHeight());
        }
        glEnd();
        glDisable(GL_TEXTURE_2D);
        glPopMatrix();
	}
	
    /**
     * Sets the position of the sprite.
     */
    public void setPosition(double newX, double newY) {
    	sc.x = newX;
    	sc.y = newY;
    }
    
    /**
     * Sets the position of the sprite.
     */
    public void setPosition(Vector2 pos) {
    	sc.x = pos.x;
    	sc.y = pos.y;
    }
    
    /**
     * Sets the position of the sprite relative to the sprite's previous position.
     */
    public void setPositionRelative(double xOff, double yOff) {
    	sc.x += xOff;
    	sc.y += yOff;
    }
    
    /**
     * Sets the position of the sprite relative to the sprite's previous position.
     */
    public void setPositionRelative(Vector2 off) {
    	sc.x += off.x;
    	sc.y += off.y;
    }
}
