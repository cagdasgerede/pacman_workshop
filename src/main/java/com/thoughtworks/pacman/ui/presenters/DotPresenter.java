package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public class DotPresenter implements Presenter, Serializable {
    private static final int SIDE = 4;
    private final Dot dot;
    private final Rectangle bounds;

    public DotPresenter(Dot dot) {
        this.dot = dot;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!dot.isEaten()) {
            graphics.setColor(Color.pink);
            graphics.fill(bounds);
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = dot.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}
