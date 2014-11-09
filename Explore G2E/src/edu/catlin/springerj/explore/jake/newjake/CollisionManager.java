package edu.catlin.springerj.explore.jake.newjake;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
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

    @Override
    public void initialize() {
    }

    @Override
    public void run() {
    }

}
