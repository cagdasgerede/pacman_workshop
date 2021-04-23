package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;
import com.thoughtworks.pacman.ui.Settings;

public class PacmanPresenter implements Presenter {
    static final int DIAMETER = 20;
    static final int MOUTH_CLOSED = 360;
    static final int MOUTH_OPENED = 280;

    static final int DEATH_FRAMES = 10;
    static final int DELAY_AFTER_DEAD = 30;
    private static final int CHEWING_FRAMES = 10;

    private final Pacman pacman;
    private long lastFrame;
    private int deadFrame;
    private int pacmanShape=0;
    private Color pacmanColor = Color.yellow;
    private Color[] colorOptions = {Color.yellow,Color.red,Color.blue,Color.green,new Color(255,204,51),new Color(153,153,153),new Color(102,51,0),new Color(102,0,153)};
    private Settings settings;
    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
        settings = new Settings();
        pacmanColor = colorOptions[settings.getPacmanColorIndex()];
        pacmanShape = settings.getPacmanShapeIndex();
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(pacmanColor);
        Rectangle bounds = getBounds();

        switch (pacmanShape) {
            case 0: graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, getStartAngle(), getArcAngle());    
                break;
            case 1:
                    graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                    drawShape(graphics,new int[] {(bounds.x+bounds.width), (bounds.x+(bounds.width/2)), (bounds.x+bounds.width)}, new int[] {bounds.x, bounds.x+(bounds.width/2), bounds.x}, 
                                new int[] {bounds.y, bounds.y+(bounds.height/2), bounds.y+(bounds.height)}, bounds.width, bounds.height, getStartAngle(), 3);
                break;
            case 2:
                    graphics.fillPolygon(new int[] {bounds.x, bounds.x+(bounds.width/2), (bounds.x+bounds.width)}, new int[] {(bounds.y+bounds.height), bounds.y, (bounds.y+bounds.height)}, 3);
                    drawShape(graphics,new int[] {(bounds.x+bounds.width), (bounds.x+(bounds.width/2)), (bounds.x+bounds.width)}, new int[] {bounds.x, bounds.x+(bounds.width/2), bounds.x}, 
                    new int[] {bounds.y, bounds.y+(4*bounds.height/6), bounds.y+(5*bounds.height/6)}, bounds.width, bounds.height, getStartAngle(), 3);
                break;
            case 3:
                    graphics.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
                    graphics.fillPolygon(new int[] {bounds.x, bounds.x+(bounds.width/2), (bounds.x+bounds.width)}, new int[] {(bounds.y+bounds.height), bounds.y, (bounds.y+bounds.height)}, 3);
                    drawShape(graphics,new int[] {(bounds.x+bounds.width), (bounds.x+(bounds.width/2)), (bounds.x+bounds.width)}, new int[] {bounds.x, bounds.x+(bounds.width/2), bounds.x}, 
                    new int[] {bounds.y, bounds.y+(4*bounds.height/6), bounds.y+(5*bounds.height/6)}, bounds.width, bounds.height, getStartAngle(), 3);
            break;
            default:
                    graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, getStartAngle(), getArcAngle()); break;
        }
    }
    
    public void drawShape(Graphics2D graphics,int[] xRight,int[] xLeft, int[] y, int width, int height,int getStartAngle,int nDimension) {
        graphics.setColor(Color.black);
        if (isDying())
            graphics.fillRect(xLeft[0], y[0], width, height);
        else if (!pacman.isMoving())
          if((2*width)==getStartAngle)
            graphics.fillPolygon(xRight, y, nDimension);
          else
            graphics.fillPolygon(xLeft, y, nDimension);
        else if(lastFrame++ % CHEWING_FRAMES < CHEWING_FRAMES / 2)
          if((2*width)==getStartAngle)
            graphics.fillPolygon(xRight, y, nDimension);
          else
            graphics.fillPolygon(xLeft, y, nDimension);
    }

    int getStartAngle() {
        return pacman.getDirection().getStartAngle();
    }

    int getArcAngle() {
        if (isDying()) {
            int angle = MOUTH_CLOSED - deadFrame * DEATH_FRAMES;
            return angle < 0 ? 0 : angle;
        }
        if (!pacman.isMoving()) {
            return MOUTH_OPENED;
        }
        return lastFrame++ % CHEWING_FRAMES < CHEWING_FRAMES / 2 ? MOUTH_CLOSED : MOUTH_OPENED;
    }

    Rectangle getBounds() {
        int radius = DIAMETER / 2;
        SpacialCoordinate upperLeft = pacman.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(DIAMETER, DIAMETER));
    }

    boolean isDying() {
        return pacman.isDead() && deadFrame++ <= (MOUTH_CLOSED / DEATH_FRAMES) + DELAY_AFTER_DEAD;
    }
}
