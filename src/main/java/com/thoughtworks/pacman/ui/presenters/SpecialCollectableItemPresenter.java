package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.SpecialCollectableItem;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class SpecialCollectableItemPresenter implements Presenter {
    private static final int SIDE = 8;
    private final SpecialCollectableItem specialCollectableItem;
    private final Rectangle bounds;

    public SpecialCollectableItemPresenter(SpecialCollectableItem specialCollectableItem) {
        this.specialCollectableItem = specialCollectableItem;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!specialCollectableItem.isEaten()) {
            if (specialCollectableItem.getKind() == 1) {
                graphics.setColor(Color.blue);
            }
            else if (specialCollectableItem.getKind() == 2) {
                graphics.setColor(Color.green);
            }
            else if (specialCollectableItem.getKind() == 3) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(Color.orange);
            }
            graphics.fill(bounds);
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = specialCollectableItem.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}