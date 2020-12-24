package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class FreezingItemPresenter implements Presenter {
    private static final int SIDE = 20;
    private final FreezingItem freezingItem;
    private final Rectangle bounds;

    public FreezingItemPresenter(FreezingItem freezingItem) {
        this.freezingItem = freezingItem;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!freezingItem.isEaten()) {
            graphics.setColor(Color.red);
            graphics.fill(bounds);
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = freezingItem.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}
