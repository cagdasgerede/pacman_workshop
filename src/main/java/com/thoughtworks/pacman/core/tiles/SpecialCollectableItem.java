package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class SpecialCollectableItem extends Tile {
    private boolean eaten;
    private int kind;

    public SpecialCollectableItem(TileCoordinate coordinate, String value, int kind) {
        super(coordinate);
        this.kind = kind;
    }

    public SpecialCollectableItem(TileCoordinate coordinate, int kind) {
        super(coordinate);
        this.kind = kind;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
        this.eaten = true;
    }

    public int getKind() {
        return kind;
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
        if (this.kind == 1) {
            return "q";
        }
        else if (this.kind == 2) {
            return "w";
        }
        else if (this.kind == 3) {
            return "e";
        }
        else {
            return "?";
        }
    }
}