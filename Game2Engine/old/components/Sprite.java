package edu.catlin.springerj.g2e.old.components;

import java.io.IOException;
import java.util.ArrayList;

import edu.catlin.springerj.g2e.old.Component;
import edu.catlin.springerj.g2e.old.StaticData;
import edu.catlin.springerj.g2e.old.events.Event;
import edu.catlin.springerj.g2e.old.math.Vector2;
import edu.catlin.springerj.g2e.old.other.Graphics;
import edu.catlin.springerj.g2e.old.other.Texture;
import edu.catlin.springerj.g2e.old.other.TextureLoader;

public class Sprite implements Component {
	protected double x, y;
	
	protected ArrayList<Texture> textures = new ArrayList();
	protected String baseSource = "";
	
	/**
	 * Loads a sprite from an image with the filename "ref".
	 */
	public Sprite(String ref) {
        try {
        	TextureLoader loader = (TextureLoader) StaticData.get("textureloader");
            textures = loader.getTexture(baseSource + ref + ".png", 1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
	/**
	 * Loads a sprite from an image with the filename "ref" from the texture loader "loader".
	 */
    public Sprite(TextureLoader loader, String ref) {
        try {
            textures = loader.getTexture(baseSource + ref + ".png", 1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
	 * Loads a sprite from an image with the filename "ref" from the texture loader "loader" with "n" animations.
	 */
    public Sprite(TextureLoader loader, String ref, int n) {
        try {
            textures = loader.getTexture(baseSource + ref + ".png", n);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

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
     * Sets the position of the sprite.
     */
    public void setPosition(double newX, double newY) {
    	x = newX;
    	y = newY;
    }
    
    /**
     * Sets the position of the sprite.
     */
    public void setPosition(Vector2 pos) {
    	x = pos.x();
    	y = pos.y();
    }
    
    /**
     * Sets the position of the sprite relative to the sprite's previous position.
     */
    public void setPositionRelative(double xOff, double yOff) {
    	x += xOff;
    	y += yOff;
    }
    
    /**
     * Sets the position of the sprite relative to the sprite's previous position.
     */
    public void setPositionRelative(Vector2 off) {
    	x += off.x();
    	y += off.y();
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
    
	@Override
	public void update() {
		Graphics.drawSprite(this, x, y);
	}

	@Override
	public void onEvent(Event e) {

	}
}
