package edu.catlin.springerj.explore.jake;

import java.io.File;

import edu.catlin.springerj.explore.rory.Keys;
import edu.catlin.springerj.explore.rory.MouseInput;
import edu.catlin.springerj.explore.enemy.Enemy;
import edu.catlin.springerj.explore.graphics.GreyStripedBackground;
import edu.catlin.springerj.explore.graphics.particle.ParticleEmitter;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import edu.catlin.springerj.explore.player.PlayerEntity;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.explore.planets.PlanetGravityManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.view.View;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.tiled.TiledObject;
import edu.catlin.springerj.g2e.tiled.TiledXMLParser;

public class Jake {
	public static void main(String[] args) {
        Core.initialize(new LWJGLManager().add(new PlanetGravityManager()).add(new CollisionManager()));

        PlayerEntity p = new PlayerEntity(new Vector2(100, 0));

        Core.getRootManager().add(new View(p));
        Core.getRootManager().add(new GreyStripedBackground());

        TiledXMLParser tmx = new TiledXMLParser(new File(Core.getResourceFolder() + "map\\tutorial-00.tmx"));
        tmx.parse();

        TiledObject object;
        while ((object = tmx.nextObject()) != null) {
            switch (object.type) {
                case "planet":
                    Core.getRootManager().add(new Planet(new Vector2(object.x+object.width/2, -object.y-object.width/2), object.width / 2));
                    break;
                case "player":
                	p.getComponent(PositionComponent.class).position = new Vector2(object.x+32/2, -object.y-32/2);
                	Core.getRootManager().add(p);
                	break;
                case "slimeenemy":
                	Core.getRootManager().add(new Enemy(new Vector2(object.x+32/2, -object.y-32/2)));
                	break;
                default:
                    break;
            }
        }
        
        Core.getRootManager().add(new ParticleEmitter(new Vector2(0.0d, 0.0d), new Vector2(10.0d, 10.0d), 100));
        
        Core.getRootManager().add(new Keys()).add(new MouseInput());

        Core.run();
	}
}
