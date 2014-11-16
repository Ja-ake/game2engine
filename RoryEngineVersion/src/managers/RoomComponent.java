package managers;

import loading.TiledObject;
import loading.TiledXMLParser;
import enemies.Enemy;
import enemies.Spawner;
import planets.Planet;
import player.PlayerEntity;
import engine.AbstractComponent;
import engine.AbstractEntity;
import engine.Vector2;
import java.io.File;
import java.util.LinkedList;

public class RoomComponent extends AbstractComponent {

    public static String path = "map/";
    public static String type = ".tmx";
    public LinkedList<AbstractEntity> list;

    public RoomComponent() {
        list = new LinkedList();
    }

    public void clear() {
        while (!list.isEmpty()) {
            list.getFirst().destroySelf();
        }
    }

    public <E extends AbstractEntity> E get(Class<E> c) {
        for (AbstractEntity e : list) {
            if (c.isInstance(e)) {
                return (E) e;
            }
        }
        return null;
    }

    public void load(String name) {
        clear();

        TiledXMLParser tmx = new TiledXMLParser(new File(path + name + type));
        tmx.parse();
        TiledObject object;
        while ((object = tmx.nextObject()) != null) {
            Vector2 pos = new Vector2(object.x + object.width / 2, -object.y - object.width / 2);
            switch (object.type) {
                case "planet":
                    new Planet(pos, object.width / 2);
                    break;
                case "player":
                    new PlayerEntity(pos);
                    break;
                case "slimeenemy":
                    new Enemy(pos);
                    break;
                case "spawner":
                    new Spawner(pos);
            }
        }
    }
}
