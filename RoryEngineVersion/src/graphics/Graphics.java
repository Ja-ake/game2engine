package graphics;

import engine.Color4d;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public abstract class Graphics {

    public static void drawLine(double x1, double y1, double x2, double y2) {
        drawLine(x1, y1, x2, y2, Color4d.BLACK);
    }

    public static void drawLine(double x1, double y1, double x2, double y2, Color4d c) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glLineWidth(2);
        c.glColor();
        glBegin(GL_LINES);
        {
            glVertex2d(x1, y1);
            glVertex2d(x2, y2);
        }
        glEnd();
        glPopMatrix();
    }

    public static void drawSprite(Texture s, double x, double y, double angle) {
        glPushMatrix();
        glEnable(GL_TEXTURE_2D);
        s.bind();
        //Translate twice to rotate at center
        glTranslated(x, y, 0);
        glRotated((double) (angle * 180 / Math.PI), 0, 0, 1f);
        glTranslated(-s.getImageWidth() / 2, -s.getImageHeight() / 2, 0);

        glColor3d(1, 1, 1);
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

    public static void fillRect(double x, double y, double w, double h, Color4d c) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        c.glColor();
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
