package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.FreezeItem;
import com.thoughtworks.pacman.ui.Presenter;

public class FreezeItemPresenter implements Presenter {
    private static final int DIMENSION = 20;
    private static final int SIDE = 4;
    private final FreezeItem freezeItem;
    private long lastFrame;

    public FreezeItemPresenter(FreezeItem freezeItem) {
        this.freezeItem = freezeItem;
    }

    public void draw(Graphics2D graphics) {
        if (!freezeItem.isVisible()) 
            return;
        Rectangle bounds = getBounds();
        if (freezeItem.getColor() == 0)
            graphics.setColor(Color.GREEN);
        else if (freezeItem.getColor() == 1)
            graphics.setColor(Color.YELLOW);
        else if (freezeItem.getColor() == 2)
            graphics.setColor(Color.RED);

        graphics.fill(bounds);
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = freezeItem.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x-4, upperLeft.y-4, SIDE+4, SIDE+4);
    }
}