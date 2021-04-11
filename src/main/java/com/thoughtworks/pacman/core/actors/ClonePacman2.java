package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;
import com.thoughtworks.pacman.core.movement.UserControlledMovementStrategy;

public class ClonePacman2 extends ClonePacman {
    public ClonePacman2(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    public ClonePacman2(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center, direction, new RandomMovementStrategy(center, maze));
    }

    public int getKind() {
        return 2;
    }
}