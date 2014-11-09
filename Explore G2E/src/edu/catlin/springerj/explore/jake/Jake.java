package edu.catlin.springerj.explore.jake;

import java.io.File;

import edu.catlin.springerj.explore.Player;
import edu.catlin.springerj.explore.jake.graphics.FilledCircle;
import edu.catlin.springerj.explore.jake.graphics.GreyStripedBackground;
import edu.catlin.springerj.explore.rory.Planet;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.game.FPSDisplay;
import edu.catlin.springerj.g2e.lwjgl.view.View;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.tiled.TiledMap;
import edu.catlin.springerj.g2e.tiled.TiledObject;
import edu.catlin.springerj.g2e.tiled.TiledTile;
import edu.catlin.springerj.g2e.tiled.TiledXMLParser;
import edu.catlin.springerj.g2e.utility.Logger;

public class Jake {
	public static void main(String[] args) {
		Core.initialize(new LWJGLManager());
		
		Player p = new Player();
		
		Core.getRootManager().add(new View(p));
		Core.getRootManager().add(new GreyStripedBackground());
		
		TiledXMLParser tmx = new TiledXMLParser(new File(Core.getResourceFolder() + "map\\tutorial-00.tmx"));
		tmx.parse();
		
//		TiledMap map = tmx.getMap();
//		TiledTile tile = null;
//		int i = 0;
//		int j = 0;
//		while ((tile = tmx.nextTile()) != null) {
//			if (i >= map.width) {
//				i = 0;
//				j++;
//			}
//			
//			Core.getRootManager().add(new Tile(tmx.getTileTexture(tile.gid), -i*map.tilewidth+512, j*map.tileheight-512));
//			i++;
//		}
		
		TiledObject object;
		while((object = tmx.nextObject()) != null) {
			switch (object.type) {
			case "planet":
				Core.getRootManager().add(new Planet(new Vector2(object.x+object.width/2, -object.y-object.width/2), object.width/2));
				break;
			case "player":
				p.getComponent(PositionComponent.class).position = new Vector2(object.x+32.0f/2, -object.y-32.0f/2);
				Core.getRootManager().add(p);
			default:
				break;
			}
		}
		
		//Core.getRootManager().add(new FilledCircle());
		//Core.getRootManager().add(p);
		
		//Core.getRootManager().add(new FPSDisplay());
		Core.run();
	}
}
