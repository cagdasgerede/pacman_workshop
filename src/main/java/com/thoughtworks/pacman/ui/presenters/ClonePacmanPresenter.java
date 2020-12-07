package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.ClonePacman;
import com.thoughtworks.pacman.ui.Presenter;

public class ClonePacmanPresenter implements Presenter {
    static final int DIAMETER = 20;
    static final int MOUTH_CLOSED = 360;
    static final int MOUTH_OPENED = 280;

    static final int DEATH_FRAMES = 10;
    static final int DELAY_AFTER_DEAD = 30;
    private static final int CHEWING_FRAMES = 10;

    private final ClonePacman clonePacman;
    private long lastFrame;
    private int deadFrame;

    public ClonePacmanPresenter(ClonePacman clonePacman) {
        this.clonePacman = clonePacman;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, getStartAngle(), getArcAngle());
    }

    int getStartAngle() {
        return clonePacman.getDirection().getStartAngle();
    }

    int getArcAngle() {
        if (isDying()) {
            int angle = MOUTH_CLOSED - deadFrame * DEATH_FRAMES;
            return angle < 0 ? 0 : angle;
        }
        if (!clonePacman.isMoving()) {
            return MOUTH_OPENED;
        }
        return lastFrame++ % CHEWING_FRAMES < CHEWING_FRAMES / 2 ? MOUTH_CLOSED : MOUTH_OPENED;
    }

    Rectangle getBounds() {
        int radius = DIAMETER / 2;
        SpacialCoordinate upperLeft = clonePacman.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(DIAMETER, DIAMETER));
    }

    boolean isDying() {
        return clonePacman.isDead() && deadFrame++ <= (MOUTH_CLOSED / DEATH_FRAMES) + DELAY_AFTER_DEAD;
    }
}
