package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.SpecialItem;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class SpecialItemPresenter implements Presenter {
    private static final int SIDE = 20;
    private final SpecialItem specialItem;
    private final Rectangle bounds;

    public SpecialItemPresenter(SpecialItem specialItem) {
        this.specialItem = specialItem;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!specialItem.isEaten()) {
            graphics.setColor(Color.red);
            graphics.fill(bounds);
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = specialItem.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}