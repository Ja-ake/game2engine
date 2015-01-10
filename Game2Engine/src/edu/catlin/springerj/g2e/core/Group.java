//package edu.catlin.springerj.g2e.core;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Group extends AbstractComponent {
//	protected List<AbstractEntity> entities;
//	protected Group.EntityRule rule;
//	protected String name;
//	
//	public Group(String n) {
//		name = n;
//		
//		rule = new Group.EntityRule() {
//			@Override
//			public boolean check(AbstractEntity e) {
//				return false;
//			}
//		};
//	}
//	
//	public abstract class EntityRule {
//		public abstract boolean check(AbstractEntity e);
//	}
//	
//	public abstract class EntityRunnable {
//		public abstract void run(AbstractEntity e);
//	}
//	
//	/**
//	 * Adds an entity to the group even if the entity does not pass the rule test. Returns true if the entity was successfully added to the group.
//	 */
//	public boolean add(AbstractEntity e) {
//		if (!entities.contains(e) && rule.check(e)) entities.add(e);
//		else return false;
//		return true;
//	}
//	
//	/**
//	 * Only adds an entity to the room if it passes the rule test. Returns true if the entity was successfully added to the group.
//	 */
//	public boolean addConditional(AbstractEntity e) {
//		if (!entities.contains(e)) entities.add(e);
//		else return false;
//		return true;
//	}
//	
//	/**
//	 * Sets the join rule.
//	 */
//	public void setRule(Group.EntityRule r) {
//		rule = r;
//	}
//	
//	/**
//	 * Returns the name of the group.
//	 */
//	public String getName() {
//		return name;
//	}
//	
//	public List<AbstractEntity> getEntities(Group.EntityRule r) {
//		List<AbstractEntity> ret = new ArrayList<AbstractEntity>();
//		for (AbstractEntity e : entities) {
//			if (r.check(e)) ret.add(e);
//		}
//		
//		return ret;
//	}
//	
//	public List<AbstractEntity> getEntities() {
//		return new ArrayList<AbstractEntity>(entities);
//	}
//	
//	public void execute(Group.EntityRunnable commands, Group.EntityRule r) {
//		for (AbstractEntity e : entities) if (r.check(e)) commands.run(e);
//	}
//	
//	public void execute(Group.EntityRunnable commands) {
//		for (AbstractEntity e : entities) commands.run(e);
//	}
// }
