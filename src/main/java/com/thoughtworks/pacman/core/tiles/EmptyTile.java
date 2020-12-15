package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

import java.io.Serializable;

public class EmptyTile extends Tile implements Serializable {

    public EmptyTile(TileCoordinate coordinate, String value) {
        super(coordinate);
    }

    public EmptyTile(TileCoordinate coordinate) {
        super(coordinate);
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
        return " ";
    }
}
