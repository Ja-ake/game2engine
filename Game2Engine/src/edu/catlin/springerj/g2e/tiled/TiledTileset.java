package edu.catlin.springerj.g2e.tiled;

import java.io.IOException;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import edu.catlin.springerj.g2e.lwjgl.util.TextureLoader;

public class TiledTileset {
	public List<Texture> textures;
	public int start;
	
	public TiledTileset(String resource, int x, int y, int s) {
		try {
			textures = TextureLoader.getTileSet(resource, x, y, GL_TEXTURE_2D, GL_RGBA, GL_NEAREST, GL_NEAREST);
			start = s;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Texture getTile(int i) {
		if ((i-start)>=0 && (i-start)<textures.size()) return textures.get(i-start);
		else return null;
	}
}
