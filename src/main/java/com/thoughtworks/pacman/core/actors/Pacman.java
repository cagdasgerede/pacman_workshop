package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.UserControlledMovementStrategy;

public class Pacman extends Actor {
    private boolean dead = false;

    public Pacman(Maze maze, String difficulty) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
        if (difficulty.equals("easy")) {
            setSpeed(110);
        } else if (difficulty.equals("medium")) {
            setSpeed(95);
        } else if (difficulty.equals("hard")) {
            setSpeed(80);
        } else {
            setSpeed(100);
        }
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, new UserControlledMovementStrategy(maze, direction), center);
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

}
