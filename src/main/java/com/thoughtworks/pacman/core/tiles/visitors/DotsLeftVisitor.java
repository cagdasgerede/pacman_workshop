package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class DotsLeftVisitor implements TileVisitor<Integer> {
    public Integer visit(Dot dot) {
        return dot.isEaten() ? 0 : 1;
    }

    public Integer visit(Wall wall) {
        return 0;
    }

    public Integer visit(EmptyTile emptyTile) {
        return 0;
    }

    public Integer visit(Door door) {
        return 0;
    }

    @Override
    public Integer visit(Dot dot, int xOffset) {
        return dot.isEaten() ? 0 : 1;
    }

    @Override
    public Integer visit(Wall wall, int xOffset) {
        return 0;
    }

    @Override
    public Integer visit(Door door, int xOffset) {
        return 0;
    }
}
