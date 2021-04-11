package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;
import com.thoughtworks.pacman.core.movement.UserControlledMovementStrategy;

public class ClonePacman extends Actor {
    private boolean dead = false;

    public ClonePacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected ClonePacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, new RandomMovementStrategy(center, maze), center);
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    protected boolean isHalted() {
        return isDead();
    }

    public void setNextDirection(Direction direction) {
        ((UserControlledMovementStrategy) movementStrategy).setNextDirection(direction);
    }

    public Direction getDirection() {
        return movementStrategy.getDirection();
    }

    public boolean isMoving() {
        return movementStrategy.isMoving();
    }

    public int getKind() {
        return 0;
    }

}
