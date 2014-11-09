package edu.catlin.springerj.explore.jake.newjake;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.math.Vector2;

import java.util.ArrayList;

public class CollisionManager extends AbstractManager {

    public ArrayList<CircleCollisionComponent> list = new ArrayList();

    @Override
    public AbstractManager autoAdd(AbstractEntity e) {
        try {
            list.add(e.get(CircleCollisionComponent.class));
        } catch (InvalidComponentException ex) {
        }
        return this;
    }
    
    public boolean collisionLine(Vector2 p1, Vector2 p2, String name) {
    	for (CircleCollisionComponent other : Core.getRootManager().getManager(CollisionManager.class).list) {
            if (name.equals(other.name)) {

            }
        }
    	return false;
    }
    
    public boolean collisionPoint(Vector2 point, String name) {
    	for (CircleCollisionComponent other : Core.getRootManager().getManager(CollisionManager.class).list) {
            if (name.equals(other.name)) {
                if (other.contains(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void run() {
    }

}
