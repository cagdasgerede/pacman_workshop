package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;

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
    private Color pacmanColor = Color.yellow;
    private Color[] colorOptions = {Color.yellow,Color.red,Color.blue,Color.green,new Color(255,204,51),new Color(153,153,153),new Color(102,51,0),new Color(102,0,153)};

    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(pacmanColor);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, getStartAngle(), getArcAngle());
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
