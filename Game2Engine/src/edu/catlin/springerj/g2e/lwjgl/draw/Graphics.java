package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.lwjgl.util.FontContainer;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import edu.catlin.springerj.g2e.math.Color4d;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public abstract class Graphics {

    public static void drawCircle(double x, double y, double size, Color4d c) {
        double detail = 50;
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glColor4d(c.r, c.g, c.b, c.a);
        glBegin(GL_TRIANGLE_FAN);
        {
            glVertex2d(x, y);
            for (double angle = 0; angle <= detail; angle++) {
                glVertex2d(x + size * Math.cos(angle / detail * Math.PI * 2), y + size * Math.sin(angle / detail * Math.PI * 2));
            }
        }
        glEnd();
        glPopMatrix();
    }

    public static void drawLine(double x1, double y1, double x2, double y2) {
        drawLine(x1, y1, x2, y2, 0, 0, 0, 1);
    }

    public static void drawLine(double x1, double y1, double x2, double y2, double r, double g, double b, double a) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glLineWidth(2);
        glColor4d(r, g, b, a);
        glBegin(GL_LINES);
        {
            glVertex2d(x1, y1);
            glVertex2d(x2, y2);
        }
        glEnd();
        glPopMatrix();
    }

    public static void drawSprite(Texture s, double x, double y, double angle, double r, double g, double b, double alpha) {
        glPushMatrix();
        glEnable(GL_TEXTURE_2D);
        s.bind();
        //Translate twice to rotate at center
        glTranslated(x, y, 0);
        glRotated((double) (angle * 180 / Math.PI), 0, 0, 1f);
        glTranslated(-s.getImageWidth() / 2, -s.getImageHeight() / 2, 0);

        glColor4d(r, g, b, alpha);
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(0, s.getImageHeight()); //Height reversed because sprite y axis upside-down
            glTexCoord2d(0, s.getHeight());
            glVertex2d(0, 0);
            glTexCoord2d(s.getWidth(), s.getHeight());
            glVertex2d(s.getImageWidth(), 0);
            glTexCoord2d(s.getWidth(), 0);
            glVertex2d(s.getImageWidth(), s.getImageHeight());
        }
        glEnd();
        glPopMatrix();
    }

    public static void drawText(String s, double x, double y) {
        drawText(s, "Default", x, y, Color.black);
    }

    public static void drawText(String s, String font, double x, double y, Color c) {
        TextureImpl.bindNone();
        FontContainer.get(font).drawString((float) x, (float) y, s, c);
    }

    public static void fillRect(double x, double y, double w, double h, double r, double g, double b) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glColor3d(r, g, b);
        glTranslated(x, y, 0);
        glBegin(GL_QUADS);
        {
            glVertex2d(0, 0);
            glVertex2d(w, 0);
            glVertex2d(w, h);
            glVertex2d(0, h);
        }
        glEnd();
        glPopMatrix();
    }
}
