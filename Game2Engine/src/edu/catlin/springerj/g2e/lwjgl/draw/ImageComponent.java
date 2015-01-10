package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;

public class ImageComponent extends AbstractComponent {
	public Texture image;

	public ImageComponent(Texture t) {
		image = t;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
