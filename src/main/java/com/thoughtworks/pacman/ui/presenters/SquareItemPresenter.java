package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;
import com.thoughtworks.pacman.core.tiles.Item;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class SquareItemPresenter implements Presenter {
    protected static final int SIDE = 12;
    protected Item item;
    protected Rectangle bounds;

    public SquareItemPresenter(){
        this.item = null;
        this.bounds = null;
    }
    
    public SquareItemPresenter(Item item) {
        this.item = item;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if(item instanceof FreezingItem){
            if (!item.isEaten()) {
                graphics.setColor(Color.red);
                graphics.fill(bounds);
            }
        }
        else if (item instanceof FreezingItemBomb){
            if (!item.isEaten()) {
                graphics.setColor(Color.blue);
                graphics.fill(bounds);
            }
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = item.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }

}
