package edu.catlin.springerj.g2e.lwjgl.util;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public class FontContainer {

    private static HashMap<String, GLFont> fontMap;

    public static void add(String gameName, String name, int style, int size) {
        Font awtFont = new Font(name, style, size);
        GLFont glFont = new GLFont(awtFont, false);
        fontMap.put(gameName, glFont);
    }

    public static void create() {
    	if (fontMap == null) fontMap = new HashMap();
        if (!fontMap.isEmpty()) return;
    	
    	System.out.println("Loading fonts");
        
        add("Default", "ChunkFiveEx", Font.PLAIN, 24);
    }

    public static GLFont get(String name) {
        return fontMap.get(name);
    }
    
    public static void drawText(String s, String font, double x, double y, Color c) {
        TextureImpl.bindNone();
        FontContainer.get(font).drawString((float) x, (float) y, s, c);
    }
    
    public static void drawText(String s, double x, double y) {
        drawText(s, "Default", x, y, Color.black);
    }
}