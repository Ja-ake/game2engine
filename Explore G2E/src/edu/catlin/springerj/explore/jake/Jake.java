package edu.catlin.springerj.explore.jake;

import java.io.File;

import edu.catlin.springerj.explore.jake.graphics.FilledCircle;
import edu.catlin.springerj.explore.jake.graphics.GreyStripedBackground;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.game.FPSDisplay;
import edu.catlin.springerj.g2e.tiled.TiledMap;
import edu.catlin.springerj.g2e.tiled.TiledTile;
import edu.catlin.springerj.g2e.tiled.TiledXMLParser;

public class Jake {
	public static void main(String[] args) {
		Core.initialize(new LWJGLManager());
		
		TiledXMLParser tmx = new TiledXMLParser(new File("C:\\dev\\tiledmaps\\planetmap.tmx"));
		tmx.parse();
		
		TiledMap map = tmx.getMap();
		TiledTile tile = null;
		int i = 0;
		int j = 0;
		while ((tile = tmx.nextTile()) != null) {
			if (i >= map.width) {
				i = 0;
				j++;
			}
			
			//Core.getRootManager().add(new Tile(tmx.getTileTexture(tile.gid), -i*map.tilewidth+512, j*map.tileheight-512));
			i++;
		}
		Core.getRootManager().add(new GreyStripedBackground());
		Core.getRootManager().add(new FilledCircle());
		
		//Core.getRootManager().add(new FPSDisplay());
		Core.run();
	}
}
