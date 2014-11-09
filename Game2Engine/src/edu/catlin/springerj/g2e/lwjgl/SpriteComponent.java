package edu.catlin.springerj.g2e.lwjgl;

import java.util.ArrayList;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.util.SpriteContainer;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import java.io.IOException;

public class SpriteComponent extends AbstractComponent {

    private ArrayList<Texture> textureArray;
    public int imageIndex;
    public boolean visible;

    public SpriteComponent() {
        this("default");
    }

    public SpriteComponent(String name) {
        setSprite(name);
        imageIndex = 0;
        visible = true;
    }
    
    public SpriteComponent(String name, int n) {
        setSprite(name, n);
        imageIndex = 0;
        visible = true;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

    public Texture getTexture() {
        return textureArray.get(imageIndex);
    }
    
    public int animationCount() {
    	return textureArray.size();
    }

    public void setSprite(String name) {
        setSprite(name, 1);
    }

    public void setSprite(String name, int n) {
        try {
        	imageIndex = 0;
            textureArray = SpriteContainer.loadSprite(name, n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
