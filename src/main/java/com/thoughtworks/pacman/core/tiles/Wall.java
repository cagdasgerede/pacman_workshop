package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.maze.Maze;

public class Wall extends Tile {

    public Wall(TileCoordinate coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public <T> T visit(TileVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "+";
    }
}
