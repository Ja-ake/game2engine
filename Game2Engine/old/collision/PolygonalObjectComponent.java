package edu.catlin.springerj.g2e.object.collision;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vertex2;

public class PolygonalObjectComponent extends AbstractComponent {
	public List<Vertex2> vertices;
	
	public PolygonalObjectComponent(double...verts) {
		if (verts.length % 2 != 0) throw new RuntimeException("Invalid number of vertices.");
		vertices = new ArrayList<Vertex2>();
		for (int i=0; i<verts.length; i+=2) {
			vertices.add(new Vertex2(verts[i], verts[i+1]));
		}
	}
	
	public PolygonalObjectComponent(Vertex2...verts) {
		List<Vertex2> vert = new ArrayList<Vertex2>();
		
		for (Vertex2 v : verts) {
			vert.add(v);
		}
		
		vertices = new ArrayList<Vertex2>(vert);
		if (vertices.size() < 3) throw new RuntimeException("Invalid number of vertices.");
	}
	
	public PolygonalObjectComponent(List<Vertex2> vert) {
		vertices = new ArrayList<Vertex2>(vert);
		if (vertices.size() < 3) throw new RuntimeException("Invalid number of vertices.");
	}

    @Override
    public void initialize(AbstractEntity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
