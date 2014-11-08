package edu.catlin.springerj.g2e.movement;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class VelocityComponent extends AbstractComponent {
	public Vector2 velocity;

        public VelocityComponent(Vector2 velocity) {
            this.velocity = velocity;
        }

        public VelocityComponent() {
            this.velocity = new Vector2();
        }
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
}
