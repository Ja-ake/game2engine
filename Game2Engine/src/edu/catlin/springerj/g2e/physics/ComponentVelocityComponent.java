package edu.catlin.springerj.g2e.physics;

import java.util.HashMap;
import java.util.Map;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;

public class ComponentVelocityComponent extends AbstractComponent {
	public Map<String, Vector2> velocity;

	public ComponentVelocityComponent() {
		this(new Vector2());
	}

	public ComponentVelocityComponent(Vector2 vel) {
		velocity = new HashMap<String, Vector2>();
		this.velocity.put("", vel);
	}

	public Vector2 get() {
		return velocity();
	}

	public Vector2 get(String k) {
		if (this.velocity.get(k) == null) this.velocity.put(k, new Vector2());
		return this.velocity.get(k);
	}

	@Override
	public void initialize(AbstractEntity e) {

	}

	public void put(String k, Vector2 v) {
		this.velocity.put(k, v);
	}

	public Vector2 velocity() {
		Vector2 ret = new Vector2();

		for (Vector2 v : velocity.values()) {
			ret = ret.add(v);
		}

		return ret;
	}
}
