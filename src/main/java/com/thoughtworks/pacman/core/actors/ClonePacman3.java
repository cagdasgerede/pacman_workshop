package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;
import com.thoughtworks.pacman.core.movement.UserControlledMovementStrategy;

public class ClonePacman3 extends Clonepacman {
    public ClonePacman3(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected ClonePacman3(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, new RandomMovementStrategy(center, maze), center);
    }

    public int getKind() {
        return 3;
    }
}