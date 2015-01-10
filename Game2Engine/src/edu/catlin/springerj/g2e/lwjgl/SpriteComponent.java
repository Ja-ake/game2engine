package edu.catlin.springerj.g2e.lwjgl;

import java.util.ArrayList;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.util.SpriteContainer;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import java.io.IOException;

public class SpriteComponent extends AbstractComponent {

	private ArrayList<Texture> textureArray;
	public double imageIndex;
	public double imageSpeed;
	public boolean visible;
	public double alpha;
	public double red = 1, green = 1, blue = 1;
	public String name;

	public SpriteComponent() {
		this("default");
	}

	public SpriteComponent(String name) {
		setSprite(name);
		imageIndex = 0;
		imageSpeed = 0;
		visible = true;
		alpha = 1.0d;
	}

	public SpriteComponent(String name, int n) {
		setSprite(name, n);
		imageIndex = 0;
		imageSpeed = 0;
		visible = true;
		alpha = 1.0d;
	}

	public SpriteComponent(String name, int n, double speed) {
		setSprite(name, n);
		imageIndex = 0;
		imageSpeed = speed;
		visible = true;
		alpha = 1.0d;
	}

	public Texture getTexture() {
		return textureArray.get((int) imageIndex % textureArray.size());
	}

	@Override
	public void initialize(AbstractEntity e) {}

	public void setSprite(String name) {
		setSprite(name, 1);
	}

	public void setSprite(String nam, int n) {
		name = nam;
		try {
			// imageIndex = 0;
			textureArray = SpriteContainer.loadSprite(name, n);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
