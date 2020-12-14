package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class SpecialItem extends Tile{
    private boolean eaten;

    public SpecialItem(TileCoordinate coordinate, String value) {
        super(coordinate);
    }

    public SpecialItem(TileCoordinate coordinate) {
        super(coordinate);
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
    public <T> T visit(TileVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "?";
    }

}
