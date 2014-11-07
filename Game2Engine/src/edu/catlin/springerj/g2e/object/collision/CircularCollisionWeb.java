package edu.catlin.springerj.g2e.object.collision;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.event.CollisionEvent;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityComponent;
import edu.catlin.springerj.g2e.web.CheckEntity;
import edu.catlin.springerj.g2e.web.WebEntity;
import edu.catlin.springerj.g2e.web.WebManager;

public class CircularCollisionWeb extends WebEntity {
	public CircularCollisionWeb() {
		super("CircularCollisionWeb", new CheckEntity() {
			@Override
			public boolean check(AbstractEntity e) {
				if (e != null) {
					try {
						if (e.getComponent(CircularCollisionComponent.class) != null) return true;
					} catch (InvalidComponentException exception) {
						return false;
					}
				}
					
				return false;
			}
		});
	}
	
	public void update() {
		List<AbstractEntity> entities = this.getEntities();
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
			this.getRootManager().getManager(EventManager.class).fire(new CollisionEvent(s.getKey(), s.getValue()));
		}
	}
}
