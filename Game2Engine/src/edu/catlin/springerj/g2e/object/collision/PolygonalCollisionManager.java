package edu.catlin.springerj.g2e.object.collision;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.event.CollisionEvent;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.math.G2EMath;
import edu.catlin.springerj.g2e.math.Segment1;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.math.Vertex2;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.utility.Logger;
import edu.catlin.springerj.g2e.web.CheckEntity;
import edu.catlin.springerj.g2e.web.WebEntity;
import edu.catlin.springerj.g2e.web.WebManager;

public class PolygonalCollisionManager extends AbstractManager {
	private WebManager webmanager = null;
	
	@Override
	public void initialize() {
		webmanager = this.getRootManager().getManager(WebManager.class);
		webmanager.register(new WebEntity("PolygonalCollisionWeb", new CheckEntity() {
			@Override
			public boolean check(AbstractEntity e) {
				try {
					e.getComponent(PolygonalObjectComponent.class); 
					e.getComponent(PositionComponent.class); 
					return true; 
				} catch(InvalidComponentException ex) { 
					return false; 
				}
			}
		}));
	}

	@Override
	public void run() {
		List<AbstractEntity> entities = webmanager.get("PolygonalCollisionWeb").getEntities();
		for (int i=0; i<entities.size(); i++) {
			for (int j=i+1; j<entities.size(); j++) {
				if (checkCollision(entities.get(i), entities.get(j))) {
					this.getRootManager().getManager(EventManager.class)
					.fire(new CollisionEvent(entities.get(i), entities.get(j)));
					Logger.println("Collided!");
				} else {
					Logger.println("Did not collide!");
				}
			}
		}
	}

	public boolean checkCollision(AbstractEntity ae, AbstractEntity be) {
		List<Vector2> axises = new ArrayList<Vector2>();
		
		List<Vertex2> ase = ae.getComponent(PolygonalObjectComponent.class).vertices;
		List<Vertex2> a = new ArrayList<Vertex2>();
		
		for (Vertex2 av : ase) {
			a.add(new Vertex2(av.x(), av.y()));
		}
		
		List<Vertex2> bse = be.getComponent(PolygonalObjectComponent.class).vertices;
		List<Vertex2> b = new ArrayList<Vertex2>();
		
		for (Vertex2 bv : bse) {
			b.add(new Vertex2(bv.x(), bv.y()));
		}
		
		PositionComponent ap = ae.getComponent(PositionComponent.class), bp = be.getComponent(PositionComponent.class);
		
		for (Vertex2 av : a) {
			av.addSelf(ap.position);
		}
		
		for (Vertex2 bv : b) {
			bv.addSelf(bp.position);
		}
		
		for (int j=1; j<a.size(); j++) {
			// get the normal of -a[j],a[j-1]-
			Vector2 v = a.get(j).sub(a.get(j-1));
			v.normalSelf();
			axises.add(v);
		}
		
		for (int j=1; j<b.size(); j++) {
			// get the normal of -b[j],b[j-1]-
			Vector2 v = b.get(j).sub(b.get(j-1));
			v.normalSelf();
			axises.add(v);
		}
		
		Logger.println("-------------");
		for (Vector2 axis : axises) {
			Segment1 iseg = new Segment1();
			Segment1 jseg = new Segment1();
			for (int i = 0; i < a.size() - 1; i++) {
				Vertex2 iref = new Vertex2(a.get(i).x(), a.get(i).y());
				Vector2 ivec = new Vector2(a.get(i + 1).x() - a.get(i).x(), a
						.get(i + 1).y() - a.get(i).y());
				for (int j = 0; j < b.size() - 1; j++) {
					Vertex2 jref = new Vertex2(b.get(j).x(), b.get(j).y());
					Vector2 jvec = new Vector2(b.get(j + 1).x() - b.get(j).x(),
							b.get(j + 1).y() - b.get(j).y());

					Segment1 isegt = projection(iref, ivec, axis);
					Segment1 jsegt = projection(jref, jvec, axis);
					
					//Logger.println("Projection:  " + (int)isegt.start() + " " + (int)isegt.stop() + "/" + (int)jsegt.start() + " " + (int)jsegt.stop());
					
					if (iseg.start() <= iseg.stop()) {
						if (iseg.start() > isegt.start()) iseg.setStart(isegt.start());
						if (iseg.stop() < isegt.stop()) iseg.setStop(isegt.stop());
					} else {
						if (iseg.start() < isegt.start()) iseg.setStart(isegt.start());
						if (iseg.stop() > isegt.stop()) iseg.setStop(isegt.stop());
					}
					
					if (jseg.start() <= jseg.stop()) {
						if (jseg.start() > jsegt.start()) jseg.setStart(jsegt.start());
						if (jseg.stop() < jsegt.stop()) jseg.setStop(jsegt.stop());
					} else {
						if (jseg.start() < jsegt.start()) jseg.setStart(jsegt.start());
						if (jseg.stop() > jsegt.stop()) jseg.setStop(jsegt.stop());
					}
				}
			}
			
			Logger.println(iseg.start() + " " + iseg.stop() + "/" + jseg.start() + " " + jseg.stop());
			Logger.println(((boolean) !iseg.overlaps(jseg)));
//			Logger.println("Test: " + (new Segment1(-15.0f, 16.0f).overlaps(new Segment1(0.0f, 47.0f))));
			if (!iseg.overlaps(jseg))
				return false;
		}
		
		return true;
	}
	
	public Segment1 projection(Vertex2 reference, Vector2 vec, Vector2 axis) {
		Segment1 seg = new Segment1(0.0f, 0.0f);
		
		double angle = -G2EMath.xytort(axis).y(); // rotation off of x-axis
		Vector2 rotatedvec = G2EMath.xyrot(vec, angle);
		Vector2 rotatedref = G2EMath.xyrot(reference, angle);
		double proj = rotatedvec.dotProduct(Vector2.EAST);
		
		seg.setStart(rotatedref.x());
		seg.setStop(rotatedref.x() + proj);
		
//		Logger.println("----------------");
//		Logger.println(vec);
//		Logger.println(rotatedvec);
//		Logger.println("Dot: " + (proj));
//		Logger.println("----------------");
		
		return seg;
	}
}
