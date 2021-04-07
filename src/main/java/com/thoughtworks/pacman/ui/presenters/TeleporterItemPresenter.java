package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.TeleporterItem;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class TeleporterItemPresenter implements Presenter {
    private static final int SIDE = 10;
    private final Rectangle bounds;
    private TeleporterItem teleporterItem;

    public TeleporterItemPresenter() {
        this.teleporterItem = null;
        this.bounds = null;
    }

    public TeleporterItemPresenter(TeleporterItem teleporterItem) {
        this.teleporterItem = teleporterItem;
        this.bounds = getBounds();
    }
    
    public void draw(Graphics2D graphics) {
        if (!teleporterItem.isEaten()) {
                switch(teleporterItem.getTeleportDistance()) {
                    case 5:
                        graphics.setColor(Color.BLUE);
                        break;
                    case 10:
                        graphics.setColor(Color.CYAN);
                        break;
                    case 15:
                        graphics.setColor(Color.MAGENTA);
                        break;
                }
            }
        else if(teleporterItem.isDropped()) {
            graphics.setColor(Color.RED);
        }
            graphics.fill(bounds);
    }
    
    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = teleporterItem.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}