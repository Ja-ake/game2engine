package edu.catlin.springerj.explore;

import java.io.File;

import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.game.FPSDisplay;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.PhysicalObject;
import edu.catlin.springerj.g2e.tiled.XMLParser;
import edu.catlin.springerj.g2e.web.WebManager;

public class Test {
	public static void maina(String[] args) {
		// triangle
		Vector2[] triangle = new Vector2[4];
		triangle[0] = new Vector2(0.0f, 0.0f);
		triangle[1] = new Vector2(10.0f, 10.0f);
		triangle[2] = new Vector2(40.0f, -10.0f);
		triangle[3] = new Vector2(50.0f, -20.0f);
		
		Core.initialize(new LWJGLManager());//.add(new WebManager()));
		//Core.getRootManager().add(new Player());
		//Core.getRootManager().add(new PhysicalObject(new Vector2(-200.0f, 0.0f), new Vector2(50.0f, -15.0f)));
		//Core.getRootManager().add(new PhysicalObject(new Vector2(200.0f, 0.0f), new Vector2(-50.0f, 15.0f)));
		Core.getRootManager().add(new PhysicalObject(new Vector2(-10.0f, -200.0f), new Vector2(0.0f, 30.0f)));
		Core.getRootManager().add(new PhysicalObject(new Vector2(10.0f, 200.0f), new Vector2(0.0f, -30.0f)));
		Core.getRootManager().add(new FPSDisplay());
		//Core.getRootManager().add(new Teather());
		
		Core.run();
	}
	
	public static void main(String[] args) {
		//Core.initialize(new LWJGLManager());
		//Core.getRootManager().add(new Player());
		
		XMLParser xml = new XMLParser(new File("C:\\dev\\xmltest.xml"));
		System.out.println(xml.get("Employees").getNodeValue());
	}
}
