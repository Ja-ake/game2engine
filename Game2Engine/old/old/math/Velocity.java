package edu.catlin.springerj.g2e.old.math;

import java.util.List;

public class Velocity {
	public class Component {
		public Vector2 current;
		public Function change;
		public double currentX;
		public double maxX;
		public String type;
		
		public Component(Vector2 direction) {
			currentX = 0.0f;
			current = direction;
			maxX = Integer.MAX_VALUE;
			change = new Function() {
				public double f(double x) {
					return x;
				}
			};
			type = "";
			
			current.normalize();
		}
		
		public Component(Vector2 direction, double m, Function f) {
			currentX = 0.0f;
			current = direction;
			change = f;
			maxX = m;
			type = "";
			
			current.normalize();
		}
		
		public Component(Vector2 direction, Function f, double m, String t) {
			currentX = 0.0f;
			current = direction;
			change = f;
			maxX = m;
			type = t;
			
			current.normalize();
		}
		
		public void update(double amount) {
			current.normalize();
			if (currentX < maxX) currentX += amount;
			else currentX = maxX;
			current.scale(change.f(currentX));
		}
	}
	
	protected List<Component> components;
	protected Vector2 velocity;
	
	public Vector2 getVelocity() { return velocity; }
	public Velocity.Component add(Velocity.Component c) { components.add(c); return c; }
	public void remove(Velocity.Component c) { components.remove(c); }
	
	public void update(double amount) {
		for (Component c : components) {
			c.update(amount);
		}
		
		
	}
}
