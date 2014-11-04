package edu.catlin.springerj.g2e.object.collision;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityComponent;
import edu.catlin.springerj.g2e.web.WebManager;

public class AABBCollisionSystem extends AbstractSystem {
	private AbstractEntity entity;
	private AABBComponent aabb;
	private PositionComponent pc;
	private VelocityComponent vc;
	
	@Override
	public void initialize(AbstractEntity ent) {
		if (this.getManager().getManager(WebManager.class).get("AABBCollisionWeb") == null) this.getManager().getManager(WebManager.class).register(new AABBCollisionWeb());
		
		entity = ent;
		aabb = ent.getComponent(AABBComponent.class);
		pc = ent.getComponent(PositionComponent.class);
		vc = ent.getComponent(VelocityComponent.class);
		
		this.getManager().getManager(WebManager.class).add(entity);
	}

	@Override
	public void update() {
		List<AbstractEntity> listae = this.getManager().getManager(WebManager.class).get("AABBCollisionWeb");
		Set<Entry<AbstractEntity, AbstractEntity>> data = new HashSet<Entry<AbstractEntity, AbstractEntity>>();
		checkAABB(data, listae, 1);
		for (Entry<AbstractEntity, AbstractEntity> s : data) {
			s.getKey().getComponent(VelocityComponent.class).velocity.normalizeSelf().scaleSelf(0.000001f);
			s.getValue().getComponent(VelocityComponent.class).velocity.normalizeSelf().scaleSelf(0.000001f);
		}
	}
	
	private void checkAABB(Set<Entry<AbstractEntity, AbstractEntity>> data, List<AbstractEntity> listae, int rec) {
		// function's time is always O(sum i=0 i->n (n-1)) where n is the number of elements in listae
		
		if (rec <= 0) throw new RuntimeException("Recursion value cannot be less than 1.");
		
		PositionComponent bepc = listae.get(rec-1).getComponent(PositionComponent.class);
		AABBComponent beaabb = listae.get(rec-1).getComponent(AABBComponent.class);
		double b0x = bepc.position.x(), 
				b0y = bepc.position.y(), 
				b1x = bepc.position.x() + beaabb.width, 
				b1y = bepc.position.y() + beaabb.height;
		for (int i=rec; i<listae.size(); i++) {
			AbstractEntity ae = listae.get(i);
			PositionComponent aepc = ae.getComponent(PositionComponent.class);
			AABBComponent aeaabb = ae.getComponent(AABBComponent.class);
				
			double a0x = aepc.position.x(), 
					a0y = aepc.position.y(), 
					a1x = aepc.position.x() + aeaabb.width, 
					a1y = aepc.position.y() + aeaabb.height;
			
//			System.out.println("a: " + a0x + "," + a0y + " " + a0x + "," + a1y + " " + a1x + "," + a0y + " " + a1x + "," + a1y 
//					+ " = b: " + b0x + "," + b0y + " " + b0x + "," + b1y + " " + b1x + "," + b0y + " " + b1x + "," + b1y);
			
			if (between(a0x, b0x, a1x) && between(a0y, b1y, a1y)) { data.add(new AbstractMap.SimpleEntry<AbstractEntity, AbstractEntity>(listae.get(i), listae.get(rec-1))); continue; }
			if (between(a0x, b1x, a1x) && between(a0y, b0y, a1y)) { data.add(new AbstractMap.SimpleEntry<AbstractEntity, AbstractEntity>(listae.get(i), listae.get(rec-1))); continue; }
			if (between(a0x, b0x, a1x) && between(a0y, b0y, a1y)) { data.add(new AbstractMap.SimpleEntry<AbstractEntity, AbstractEntity>(listae.get(i), listae.get(rec-1))); continue; }
			if (between(a0x, b1x, a1x) && between(a0y, b1y, a1y)) { data.add(new AbstractMap.SimpleEntry<AbstractEntity, AbstractEntity>(listae.get(i), listae.get(rec-1))); continue; }
		}
		
		if (++rec < listae.size()) checkAABB(data, listae, rec); // recursion! yay!
	}
	
	private boolean between(double a, double b, double c) {
		if (Math.abs(a-b) <= Math.abs(a-c) && Math.abs(c-b) <= Math.abs(a-c)) return true;
		else return false;
	}
}
