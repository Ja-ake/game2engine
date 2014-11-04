package edu.catlin.springerj.g2e.object.collision;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityComponent;
import edu.catlin.springerj.g2e.web.WebManager;

public class CircularCollisionSystem extends AbstractSystem {
	private CircularCollisionComponent ccc;
	
	@Override
	public void initialize(AbstractEntity ent) {
		if (this.getManager().getManager(WebManager.class).get("CircularCollisionWeb") == null) this.getManager().getManager(WebManager.class).register(new CircularCollisionWeb());
		
		ccc = ent.getComponent(CircularCollisionComponent.class);
	}

	@Override
	public void update() {
		List<AbstractEntity> entities = this.getManager().getManager(WebManager.class).get("CircularCollisionWeb");
		Set<Entry<AbstractEntity, AbstractEntity>> data = new HashSet<Entry<AbstractEntity, AbstractEntity>>();

		for (int i=0; i<entities.size(); i++) {
			for (int j=i+1; j<entities.size(); j++) {
				PositionComponent ipc = entities.get(i).getComponent(PositionComponent.class);
				PositionComponent jpc = entities.get(j).getComponent(PositionComponent.class);
				CircularCollisionComponent iccc = entities.get(i).getComponent(CircularCollisionComponent.class);
				CircularCollisionComponent jccc = entities.get(j).getComponent(CircularCollisionComponent.class);
				double ix = ipc.position.x(), iy = ipc.position.y(), ir = iccc.radius, jx = jpc.position.x(), jy = jpc.position.y(), jr = jccc.radius;
				
				if (Math.sqrt((((jx-ix)*(jx-ix))+((jy-iy)*(jy-iy)))) < (ir + jr)) { data.add(new AbstractMap.SimpleEntry<AbstractEntity, AbstractEntity>(entities.get(i), entities.get(j))); continue; }
			}
		}
		
		for (Entry<AbstractEntity, AbstractEntity> s : data) {
			s.getKey().getComponent(VelocityComponent.class).velocity.normalizeSelf().scaleSelf(0.000001f);
			s.getValue().getComponent(VelocityComponent.class).velocity.normalizeSelf().scaleSelf(0.000001f);
		}
		
		//System.out.println(data.size());
	}
}
