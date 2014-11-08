package edu.catlin.springerj.explore.jake.graphics;

import java.io.IOException;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.BackgroundImageRenderSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.ImageComponent;
import edu.catlin.springerj.g2e.lwjgl.draw.ImageRepeatComponent;
import edu.catlin.springerj.g2e.lwjgl.util.TextureLoader;

public class GreyStripedBackground extends AbstractEntity {

	@Override
	public void initialize() {
		try {
			add(new ImageComponent(TextureLoader.getTexture(Core.getResourceFolder() + "tileset\\un.png", 1).get(0)));
			add(new ImageRepeatComponent(8, 8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		add(new BackgroundImageRenderSystem());
	}

	@Override
	public void update() {
		
	}

}
