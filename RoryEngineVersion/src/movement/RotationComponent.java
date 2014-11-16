package movement;

import engine.AbstractComponent;

public class RotationComponent extends AbstractComponent {

    public double rot;
    public double aVel;

    public RotationComponent() {
        rot = 0;
        aVel = 0;
    }
}
