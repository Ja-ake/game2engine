package edu.catlin.springerj.g2e.lwjgl;

import java.util.ArrayList;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;

public class SpriteComponent extends AbstractComponent {
	public double x, y;
	public ArrayList<Texture> textures = new ArrayList<Texture>();
	
    /**
     * Returns the width of the sprite.
     */
    public int getWidth() {
        return textures.get(0).getImageWidth();
    }

    /**
     * Returns the height of the sprite.
     */
    public int getHeight() {
        return textures.get(0).getImageHeight();
    }

    /**
     * Returns the texture of animation number "index".
     */
    public Texture getTexture(int index) {
        return textures.get(index % textures.size());
    }
    
    /**
     * Returns x coordinate of sprite.
     */
    public double getX() {
    	return x;
    }
    
    /**
     * Returns y coordinate of sprite.
     */
    public double getY() {
    	return y;
    }
}
