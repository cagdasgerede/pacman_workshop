package com.thoughtworks.pacman.core;

import java.util.ArrayList;
import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.maze.Maze;

public abstract class AllSpecialItems extends Tile {

    public AllSpecialItems(TileCoordinate coordinate, String value) {
        super(coordinate);
    }

    public AllSpecialItems(TileCoordinate coordinate) {
        super(coordinate);
    }
    public abstract boolean isEaten();
    public abstract void eat();
    public abstract void feature(Ghosts ghosts,Maze maze);
    public abstract void reset(Maze maze);
}