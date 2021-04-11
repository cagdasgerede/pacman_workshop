package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class TeleporterItem extends Tile{
    private boolean eaten;
    private final int teleportDistance;
    private boolean isDropped;
    
    public TeleporterItem(TileCoordinate coordinate, int teleportDistance, boolean isDropped) {
        super(coordinate);
        this.teleportDistance = teleportDistance;
        this.isDropped = isDropped;
    }
    
    public int getTeleportDistance() {
        return this.teleportDistance;
    }

    public void setIsDropped(boolean isDropped) {
        this.isDropped = isDropped; 
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
        this.eaten = true; 
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isDropped() {
        return isDropped;
    }

    @Override
    public <T> T visit(TileVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "*";
    }
}
