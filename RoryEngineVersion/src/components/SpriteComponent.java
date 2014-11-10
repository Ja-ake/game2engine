package components;

import engine.AbstractComponent;
import engine.Color4d;
import graphics.SpriteContainer;
import graphics.Texture;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteComponent extends AbstractComponent {

    private ArrayList<Texture> textureArray;
    public double imageIndex;
    public double imageSpeed;
    public boolean visible;
    public Color4d color;

    public SpriteComponent(String name, int n, double speed) {
        setSprite(name, n);
        imageIndex = 0;
        imageSpeed = speed;
        visible = true;
        color = new Color4d();
    }

    public SpriteComponent(String name, int n) {
        this(name, n, 0);
    }

    public SpriteComponent(String name) {
        this(name, 1);
    }

    public Texture getTexture() {
        return textureArray.get((int) imageIndex % textureArray.size());
    }

    public void setSprite(String name) {
        setSprite(name, 1);
    }

    public void setSprite(String name, int n) {
        try {
            textureArray = SpriteContainer.loadSprite(name, n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
