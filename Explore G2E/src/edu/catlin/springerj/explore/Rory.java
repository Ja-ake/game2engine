package edu.catlin.springerj.explore;

import java.io.File;
import edu.catlin.springerj.explore.jake.graphics.GreyStripedBackground;
import edu.catlin.springerj.explore.rory.CollisionManager;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.explore.planets.PlanetGravityManager;
import edu.catlin.springerj.explore.rory.PlayerEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.view.View;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.tiled.TiledObject;
import edu.catlin.springerj.g2e.tiled.TiledXMLParser;

public class Rory {

    public static void main(String[] args) {
        Core.initialize(new LWJGLManager().add(new PlanetGravityManager()).add(new CollisionManager()));

        PlayerEntity p = new PlayerEntity(new Vector2(100, 0));

        Core.getRootManager().add(new View(p));
        Core.getRootManager().add(new GreyStripedBackground());
        Core.getRootManager().add(p);
        Core.getRootManager().add(new Keys()).add(new MouseInput());

        TiledXMLParser tmx = new TiledXMLParser(new File(Core.getResourceFolder() + "map\\tutorial-00.tmx"));
        tmx.parse();

        TiledObject object;
        while ((object = tmx.nextObject()) != null) {
            switch (object.type) {
                case "planet":
                    Core.getRootManager().add(new Planet(new Vector2(object.x, -object.y), object.width / 2));
                    break;
                case "player":
                //p.getComponent(PositionComponent.class).position = new Vector2(object.x, -object.y);
                //Core.getRootManager().add(p);
                default:
                    break;
            }
        }

        Core.run();
    }
}
