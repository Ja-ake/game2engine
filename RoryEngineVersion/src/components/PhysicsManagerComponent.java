package components;

import engine.AbstractComponent;
import java.util.ArrayList;

public class PhysicsManagerComponent extends AbstractComponent {

    public ArrayList<PhysicsComponent> physicsComponentList;

    public PhysicsManagerComponent() {
        physicsComponentList = new ArrayList();
    }
}
