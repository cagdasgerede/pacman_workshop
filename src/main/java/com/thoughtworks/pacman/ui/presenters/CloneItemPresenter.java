package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.CloneItem;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class CloneItemPresenter implements Presenter {
    private static final int SIDE = 12;
    private final CloneItem ci;
    private final Ellipse2D bounds;

    public CloneItemPresenter(CloneItem ci) {
        this.ci = ci;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!ci.isEaten()) {
            graphics.setColor(Color.red);
            graphics.fill(bounds);
        }
    }

    public Ellipse2D getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = ci.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Ellipse2D.Double(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}
